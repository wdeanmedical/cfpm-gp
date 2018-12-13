package com.wdeanmedical.external.fhir;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="patients")
public class PatientsFHIR {
  
  private List<org.hl7.fhir.Patient> patient = new ArrayList<org.hl7.fhir.Patient>();

  public List<org.hl7.fhir.Patient> getPatient() {
    return patient;
  }

  public void setPatient(List<org.hl7.fhir.Patient> patient) {
    this.patient = patient;
  }  
  
}
