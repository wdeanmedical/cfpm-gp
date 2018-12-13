package com.wdeanmedical.entity.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.USState;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -4726474534029613613L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String STATUS_NEW = "new";
  public static final String STATUS_SENT = "sent";
  public static final String STATUS_READ = "read";
  public static final String STATUS_PAID = "paid";
  
  private Integer clinicanId;
  private String city;
  private String description;
  private Date dueDate;
  private String invoiceNumber;
  private String invoiceStatus;
  private Date issueDate;
  private Integer patientId;
  private String patientName;
  private String primaryPhone;
  private String postalCode;
  private Float subtotal = new Float(0);
  private Float salesTax = new Float(0);
  private String title;
  private String streetAddress1;
  private Float total = new Float(0);
  private Integer userId;
  private USState usState;
  
  private List<InvoiceLineItem> invoiceLineItemList;
  
  

 

  

  
  @Transient  
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  
  @Column(name = "clinician_id")
  public Integer getClinicanId() { return clinicanId; }
  public void setClinicanId(Integer clinicanId) { this.clinicanId = clinicanId; }
  
  @Column(name = "description")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
    
  @Column(name = "due_date")
  public Date getDueDate() { return dueDate; }
  public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
  
  @Column(name = "invoice_status")
  public String getInvoiceStatus() { return invoiceStatus; }
  public void setInvoiceStatus(String invoiceStatus) { this.invoiceStatus = invoiceStatus; }
  
  @Column(name = "issue_date")
  public Date getIssueDate() { return issueDate; }
  public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
  
  @Column(name = "invoice_number")
  public String getInvoiceNumber() { return invoiceNumber; }
  public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Transient 
  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }
  
  @Transient  
  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
  
  @Transient  
  public String getPrimaryPhone() { return primaryPhone; }
  public void setPrimaryPhone(String primaryPhone) { this.primaryPhone = primaryPhone; }  
  
  @Column(name = "sales_tax")
  public Float getSalesTax() { return salesTax; }
  public void setSalesTax(Float salesTax) { this.salesTax = salesTax; }
  
  @Transient  
  public String getStreetAddress1() { return streetAddress1; }
  public void setStreetAddress1(String streetAddress1) { this.streetAddress1 = streetAddress1; }
  
  @Column(name = "subtotal")
  public Float getSubtotal() { return subtotal; }
  public void setSubtotal(Float subtotal) { this.subtotal = subtotal; }
  
  @Column(name = "title")
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  @Column(name = "total")
  public Float getTotal() { return total; }
  public void setTotal(Float total) { this.total = total; }
  
  @Column(name = "user_id")
  public Integer getUserId() { return userId; }
  public void setUserId(Integer userId) { this.userId = userId; }
  
  @Transient  
  public USState getUsState() { return usState; }
  public void setUsState(USState usState) { this.usState = usState; }
  
  @Transient  
  public List<InvoiceLineItem> getInvoiceLineItemList() { return invoiceLineItemList; }
  public void setInvoiceLineItemList(List<InvoiceLineItem> invoiceLineItemList) { this.invoiceLineItemList = invoiceLineItemList; }
  
}
