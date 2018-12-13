package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "invoice_line_item")
public class InvoiceLineItem extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -8900104245833220448L;
  public static String[] PHI_FIELDS = new String[] {};
  
  Integer invoiceId;
  String  description;
  Float price;
  
  
  @Column(name = "invoice_id")
  public Integer getInvoiceId() { return invoiceId; }
  public void setInvoiceId(Integer invoiceId) { this.invoiceId = invoiceId; }
  
  @Column(name = "description")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  @Column(name = "price")
  public Float getPrice() { return price; }
  public void setPrice(Float price) { this.price = price; }
  
}
