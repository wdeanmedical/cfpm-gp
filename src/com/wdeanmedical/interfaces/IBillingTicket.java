package com.wdeanmedical.interfaces;

import java.util.List;

public interface IBillingTicket {

  Integer getPatientId();

  Integer getClinicianId();

  Integer getId();

  void setEntries(List<IBillingTicketEntry> arrayList);
  Class<?> getEntryClass();
}
