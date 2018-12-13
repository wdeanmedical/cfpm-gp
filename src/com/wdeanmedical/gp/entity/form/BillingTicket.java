package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.interfaces.IBillingTicket;
import com.wdeanmedical.interfaces.IBillingTicketEntry;
import com.wdeanmedical.persistence.BaseDAO;

@Entity
@Table(name = "billing_ticket")
public class BillingTicket extends BaseEntity implements Serializable, IBillingTicket {
  private static final long serialVersionUID = 2348207947269433572L;

  public static String[] PHI_FIELDS = new String[] {};

  Integer patientId;  
  Integer clinicianId;  
  
  private List<BillingTicketEntry> entries = new ArrayList<BillingTicketEntry>();

  public BillingTicket() {
  }

  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "clinician_id")
  public Integer getClinicianId() { return clinicianId; }
  public void setClinicianId(Integer clinicianId) { this.clinicianId = clinicianId; }

  @Transient
  public List<BillingTicketEntry> getEntries() { return entries; }
  public void setEntries(List<IBillingTicketEntry> entries) { 
    this.entries = BaseDAO.cast(BillingTicketEntry.class, entries); 
  }  
  
  @Transient
  public Class<?> getEntryClass() {
    return BillingTicketEntry.class;
  }  
}