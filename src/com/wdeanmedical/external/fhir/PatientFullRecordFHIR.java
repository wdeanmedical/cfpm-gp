package com.wdeanmedical.external.fhir;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "patientFullRecord")
public class PatientFullRecordFHIR {

  private org.hl7.fhir.Patient patient;

  private List<org.hl7.fhir.SensitivityType> sensitivityTypes = new ArrayList<org.hl7.fhir.SensitivityType>();

  private List<org.hl7.fhir.Questionnaire> questionnaires = new ArrayList<org.hl7.fhir.Questionnaire>();

  private List<org.hl7.fhir.MedicationAdministration> medicationAdministrations = new ArrayList<org.hl7.fhir.MedicationAdministration>();

  private List<org.hl7.fhir.RelatedPerson> relatedPersons = new ArrayList<org.hl7.fhir.RelatedPerson>();

  private List<org.hl7.fhir.Encounter> encounters = new ArrayList<org.hl7.fhir.Encounter>();

  public org.hl7.fhir.Patient getPatient() {
    return patient;
  }

  public void setPatient(org.hl7.fhir.Patient patient) {
    this.patient = patient;
  }

  public List<org.hl7.fhir.SensitivityType> getSensitivityTypes() {
    return sensitivityTypes;
  }

  public void setSensitivityTypes(List<org.hl7.fhir.SensitivityType> sensitivityTypes) {
    this.sensitivityTypes = sensitivityTypes;
  }

  public List<org.hl7.fhir.Questionnaire> getQuestionnaires() {
    return questionnaires;
  }

  public void setQuestionnaires(List<org.hl7.fhir.Questionnaire> questionnaires) {
    this.questionnaires = questionnaires;
  }

  public List<org.hl7.fhir.MedicationAdministration> getMedicationAdministrations() {
    return medicationAdministrations;
  }

  public void setMedicationAdministrations(List<org.hl7.fhir.MedicationAdministration> medicationAdministrations) {
    this.medicationAdministrations = medicationAdministrations;
  }

  public List<org.hl7.fhir.RelatedPerson> getRelatedPersons() {
    return relatedPersons;
  }

  public void setRelatedPersons(List<org.hl7.fhir.RelatedPerson> relatedPersons) {
    this.relatedPersons = relatedPersons;
  }

  public List<org.hl7.fhir.Encounter> getEncounters() {
    return encounters;
  }

  public void setEncounters(List<org.hl7.fhir.Encounter> encounters) {
    this.encounters = encounters;
  }

}
