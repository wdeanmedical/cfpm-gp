package com.wdeanmedical.service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.mysql.jdbc.StringUtils;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;
import com.stripe.net.RequestOptions;
import com.wdeanmedical.core.Code;
import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.BillingDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.form.Invoice;
import com.wdeanmedical.entity.transaction.pending.Pending;
import com.wdeanmedical.entity.transaction.pending.stripe.ChargePendingTransaction;
import com.wdeanmedical.entity.transaction.pending.stripe.CreateUpdatePendingTransaction;
import com.wdeanmedical.entity.transaction.pending.stripe.DeletePendingTransaction;
import com.wdeanmedical.entity.transaction.pending.stripe.StripePendingTransaction;
import com.wdeanmedical.interfaces.ICanExecute;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IPending;
import com.wdeanmedical.model.ValidationResult;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.MailHandler;
import com.wdeanmedical.util.Util;

public class BillingService extends AppService {

  private static Log log = LogFactory.getLog(BillingService.class);

  StripePendingTransaction pending;

  public BillingService() throws MalformedURLException {
    super();
    context = Core.servletContext;
    Stripe.apiKey = Core.stripeTestKey;
  }
  private void setPendingTransaction(StripePendingTransaction pending) {
    this.pending = pending;
  }
  private ICanExecute getICanExecute(final BillingDTO dto) throws Exception {
    final Patient patient = getPatient(dto);
    return new ICanExecute() {
      @Override
      public Boolean can() {
        StripePendingTransaction pending = appDAO.findOne(StripePendingTransaction.class, new ICriteriaTransformer() {
          @Override
          public void transform(Criteria criteria) {
            criteria.createAlias("patient", "patient");
            criteria.add(Restrictions.eq("patient.id", patient.getId()));
          }	
        });
        setPendingTransaction(pending);
        return pending == null;
      }
    };
  }

  private IPending getIPending(BillingDTO dto, Class<? extends StripePendingTransaction> Klass) throws Exception, IllegalAccessException {
    final Patient patient = getPatient(dto);
    final Pending pending = Klass.newInstance();

    return new IPending() {
      @Override
      public void create() throws Exception {
        pending.setPatient(patient);
        appDAO.create(pending);
      }

      @Override
      public void destroy() throws Exception {
        appDAO.delete(pending);
      }

      @Override
      public void setErrorMessage(String errorMessage) throws Exception {
        pending.setErrorMessage(errorMessage);		
        appDAO.update(pending);
      }

    };
  }

  String[] CHARGE_TRANSACTION_TYPES= { "Charge"};
  public void chargeCustomerStatus(BillingDTO dto) throws Exception {
    unlessTransactionPending(dto, new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        return null;
      }}, CHARGE_TRANSACTION_TYPES); 
  }  

  public void chargeCustomer(final BillingDTO dto, final HttpServletRequest request)  throws Exception {
    getBackgroundService(dto).execute(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        _chargeCustomer(dto, request);
        return null;
      }
    }, getIPending(dto, ChargePendingTransaction.class));
  }

  private void setCustomerKey(Patient patient, String key) throws Exception {
    Guardian guardian = getPatientService().getPatientGuardian(patient.getId());
    if (guardian == null) {
      patient.setCustomerKey(key);
      appDAO.update(patient);
    }
    else {
      guardian.setCustomerKey(key);
      appDAO.update(guardian);
    }

  }
  public void _chargeCustomer(BillingDTO dto, HttpServletRequest request)  throws Exception {
    Map<String,String> customAttributes = new HashMap<String,String>();
    _getCustomer(dto);
    if (dto.object != null) {
      Customer customer = (Customer)dto.object;
      Invoice invoice = patientDAO.findInvoice(dto.invoiceId);
      Map<String, Object> chargeParams = new HashMap<String, Object>();
      dto.amount = invoice.getTotal();
      chargeParams.put("amount", (int)(dto.amount * 100));
      chargeParams.put("currency", "usd");
      chargeParams.put("customer", customer.getId());
      dto.description = "invoice #" + invoice.getInvoiceNumber();
      chargeParams.put("description", dto.description);
      Charge charge = Charge.create(chargeParams);
      invoice.setInvoiceStatus(Invoice.STATUS_PAID);
      appDAO.update(invoice);
      log.info("Charge Captured: " + charge.getCaptured());
      Patient patient = appDAO.findById(Patient.class, invoice.getPatientId());
      DataEncryptor.decryptPatient(patient);
      patient.setGuardian(getPatientService().getPatientGuardian(patient.getId()));
      String patientName = Util.buildFullName(patient.getFirstName(), patient.getMiddleName(), patient.getLastName());   
      customAttributes.put("name", patientName);
      String emailTitle = "Invoice paid from " + Core.practiceClientProperties.getProperty("app.business_name");
      MailHandler.sendSystemEmail("invoice_payment", emailTitle, null, null, request, "", customAttributes, patient.getEmail());
      logEvent(dto, ActivityLog.CHARGE_CUSTOMER, "BillingService chargeCustomer()", null, null);
    }
  }

  public String createPlan(BillingDTO dto) throws Exception {
    Map<String, Object> planParams = new HashMap<String, Object>();
    planParams.put("amount", dto.planAmount);
    planParams.put("interval", dto.planInterval);
    planParams.put("name", dto.planName);
    planParams.put("currency", dto.planCurrency);
    planParams.put("id", dto.planId);
    Plan plan = Plan.create(planParams);
    log.info("Plan created: " + plan.getId());
    return plan.getId();
  }

  public String createSubscription(BillingDTO dto) throws Exception {
    Customer cu = Customer.retrieve(dto.customerId);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("plan", dto.planId);
    Subscription subscription = cu.createSubscription(params);
    log.info("Subscription created: " + subscription.getId());
    return subscription.getId();
  }

  private void createNewCustomer(BillingDTO dto) throws Exception {
    RequestOptions cardSupportedRequestOptions = RequestOptions.builder().setStripeVersion(Core.stripeApiVersion).build();
    Map<String, Object> defaultCardParams = new HashMap<String, Object>();
    defaultCardParams.put("number", dto.cardNumber);
    defaultCardParams.put("exp_month", dto.expMonth);
    defaultCardParams.put("exp_year", dto.expYear);
    defaultCardParams.put("cvc", dto.cvc);
    defaultCardParams.put("name", dto.cardholderName);
    defaultCardParams.put("address_line1", dto.streetAddress);
    defaultCardParams.put("address_city", dto.city);
    defaultCardParams.put("address_zip", dto.postalCode);
    defaultCardParams.put("address_state", dto.usStateCode);
    defaultCardParams.put("address_country", "USA");

    Map<String, Object> defaultCustomerParams = new HashMap<String, Object>();
    defaultCustomerParams.put("card", defaultCardParams);
    defaultCustomerParams.put("description", dto.companyName);

    Customer customer = new Customer();
    try {
      customer = Customer.create(defaultCustomerParams, cardSupportedRequestOptions);
    } catch(CardException exception) {
      dto.setError(new ValidationResult().invalid(exception.getMessage()));	 
      return;
    }
    String customerId=customer.getId();
    if(StringUtils.isNullOrEmpty(customerId))return;
    log.info("Customer Id: " + customer.getId());
    String encryptedKey = DataEncryptor.encrypt(customer.getId());
    setCustomerKey(getPatient(dto), encryptedKey);
  }

  private void updateExistingCustomer(BillingDTO dto) throws Exception {
    Customer customer = (Customer)dto.object;
    Card card = (Card) customer.getSources().retrieve(customer.getDefaultSource());
    Map<String, Object> updateParams = new HashMap<String, Object>();

    updateParams.put("exp_month", dto.expMonth);
    updateParams.put("exp_year", dto.expYear);
    updateParams.put("name", dto.cardholderName);
    updateParams.put("address_line1", dto.streetAddress);
    updateParams.put("address_city", dto.city);
    updateParams.put("address_zip", dto.postalCode);
    updateParams.put("address_state", dto.usStateCode);

    if (StringUtils.isNullOrEmpty(dto.cardNumber)) {
      card.update(updateParams);
      logEvent(dto, ActivityLog.UPDATE_CREDIT_CARD, "BillingService createOrUpdateCustomer()", null, null);
    }
    else {
      updateParams.put("number", dto.cardNumber);
      updateParams.put("cvc", dto.cvc);
      customer.update(updateParams);
      logEvent(dto, ActivityLog.REPLACE_CREDIT_CARD, "BillingService createOrUpdateCustomer()", null, null);
    }
  }

  public void createOrUpdateCustomer(final BillingDTO dto) throws Exception {
    getBackgroundService(dto).execute(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        getCustomer(dto); 
        if (dto.object == null) {
          createNewCustomer(dto);
        }
        else {
          updateExistingCustomer(dto);
        }
        return null;
      }
    },getIPending(dto, CreateUpdatePendingTransaction.class));  
  }

  private void _deleteCustomer(BillingDTO dto) throws Exception {
    _getCustomer(dto); 
    if (dto.object != null) {
      Customer customer = (Customer)dto.object;
      customer.delete();
      setCustomerKey(getPatient(dto), null);
    }  
  }

  private BackgroundService getBackgroundService(BillingDTO dto) throws Exception {
    return BackgroundService.getInstance(getICanExecute(dto));
  }

  public void deleteCustomer(final BillingDTO dto) throws Exception {
    getBackgroundService(dto).execute(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        _deleteCustomer(dto);
        return null;
      }  
    }, getIPending(dto, DeletePendingTransaction.class));
  }

  private Patient getPatient(BillingDTO dto) throws Exception {
    ClientSession clientSession = appDAO.findClientSessionBySessionId(dto.sessionId);
    return clientSession.getPatient();
  }

  private Boolean transactionInProgress(BillingDTO dto, final String[] types) throws Exception {
    final Patient patient=getPatient(dto);
    StripePendingTransaction pending = appDAO.findOne(StripePendingTransaction.class, new ICriteriaTransformer() {
      @Override
      public void transform(Criteria criteria) {
        criteria.createAlias("patient", "patient");
        criteria.add(Restrictions.eq("patient.id", patient.getId()));
        criteria.add(Restrictions.in("type", types));
      } 
    }); 
    return pending != null;
  }
  private void unlessTransactionPending(BillingDTO dto, Callable<Void> callable, String[] types) throws Exception {
    if (transactionInProgress(dto, types)) {
      String errMessage=null;
      if(pending!=null) {
        errMessage= pending.getErrorMessage();
        if(!StringUtils.isNullOrEmpty(errMessage)) {
          log.info("Stripe Error ("+ pending.getClass().getName()+"):" + errMessage);
          errMessage="Stripe Error: Refresh and retry last action or contact site administrator if error persists.";
          appDAO.delete(pending);
        }
      }
      ValidationResult error = new ValidationResult().invalid(errMessage);
      error.setErrorType(Code.toType("Pending Transaction"));	 
      dto.setError(error);
    } else {
      callable.call();
    }
  }
  private void _getCustomer(BillingDTO dto) throws Exception {
    String encryptedKey = null;
    Patient patient=getPatient(dto);
    Guardian guardian = getPatientService().getPatientGuardian(patient.getId());
    if (guardian == null) {
      encryptedKey = patient.getCustomerKey();
    }
    else {
      encryptedKey = guardian.getCustomerKey();
    }
    if (!StringUtils.isNullOrEmpty(encryptedKey)) {
      String key = DataEncryptor.decrypt(encryptedKey);
      Customer customer = Customer.retrieve(key);
      dto.object = customer;
    }
  }
  String[] CUSTOMER_TRANSACTION_TYPES = {"Delete","CreateUpdate"};
  public void getCustomer(final BillingDTO dto) throws Exception {  
    unlessTransactionPending(dto, new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        _getCustomer(dto);
        return null;
      }
    }, CUSTOMER_TRANSACTION_TYPES); 
  }
}
