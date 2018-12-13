package com.wdeanmedical.dto; 

import java.util.ArrayList;
import java.util.List;

import com.wdeanmedical.model.Card;

public class BillingDTO extends AppDTO {

  public Float amount;
  public String cardNumber;
  public String cardholderName;
  public String city;
  public String companyName;
  public String customerId;
  public Integer cvc; 
  public String expiration;
  public Integer expMonth; 
  public Integer expYear; 
  public Integer invoiceId; 
  public String lastFour;
  public Integer planAmount;
  public String planId;
  public String planInterval;
  public String planCurrency;
  public String planName;
  public String postalCode;
  public String streetAddress;
  public String usStateCode;
  
  public List<Card>	cards = new ArrayList<Card>();
}
