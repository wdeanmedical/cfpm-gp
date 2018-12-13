package com.wdeanmedical.service;


import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.dto.AppDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.ClientSession;
import com.wdeanmedical.entity.Country;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.MaritalStatus;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.external.fhir.PatientFullRecordFHIR;
import com.wdeanmedical.external.fhir.PatientsFHIR;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.util.DataEncryptor;

public class ExternalService extends AppService {

  private static Log log = LogFactory.getLog(ExternalService.class);
 public static int RETURN_CODE_INVALID_PASSWORD = -2;
  
  private PatientDAO patientDAO;
  private PatientService patientService;


  public ExternalService() throws MalformedURLException {
    super();
    patientDAO = (PatientDAO) wac.getBean("patientDAO");
    patientService = new PatientService();
  }
  
  
  public AppDTO auth(AppDTO dto, String ipAddress) throws Exception {
    dto.authenticated = false;
    User user = (User)appDAO.authenticateClient(dto.username, dto.password, Client.USER);
    if (user.getAuthStatus() == Client.STATUS_AUTHORIZED) {
      ClientSession clientSession = new ClientSession();
      clientSession.setUser(user);
      clientSession.setSessionId(user.getSessionId());
      clientSession.setIpAddress(ipAddress);
      clientSession.setLastAccessTime(new Date());
      clientSession.setParked(false);
      appDAO.create(clientSession);
      dto.userId = user.getId();
      dto.authenticated = true;
      dto.sessionId = user.getSessionId();
    }
    logEvent(dto, ActivityLog.LOGIN, "ExternalService auth()", null, null);
    return dto;
  }
  
  public org.hl7.fhir.Patient getPatient(String mrn) throws Exception{
    Patient patient = patientDAO.findPatientByMrn(mrn);
    DataEncryptor.decryptPatient(patient);
    return getPatientFHIR(patient);
  }
  
  private org.hl7.fhir.Patient getPatientFHIR(Patient patient) throws Exception{
    org.hl7.fhir.Patient fhirpatient = new org.hl7.fhir.Patient();
    org.hl7.fhir.DateTime birthDate = new org.hl7.fhir.DateTime();
    birthDate.setValue(patient.getDob().toString());
      
    fhirpatient.setBirthDate(birthDate);
      
    org.hl7.fhir.Identifier identifier = new org.hl7.fhir.Identifier();
    org.hl7.fhir.IdentifierUse identifierUse = new  org.hl7.fhir.IdentifierUse();
    identifierUse.setId(org.hl7.fhir.IdentifierUseList.USUAL.value());
    identifier.setUse(identifierUse);
    org.hl7.fhir.String mrn = new org.hl7.fhir.String();
    mrn.setValue("MRN");
    identifier.setLabel(mrn);
    org.hl7.fhir.String mrnValue = new org.hl7.fhir.String();
    mrnValue.setValue(patient.getMrn());
    identifier.setValue(mrnValue);
    fhirpatient.getIdentifier().add(identifier);
    
    org.hl7.fhir.CodeableConcept maritalStatusCodeableConcept = new org.hl7.fhir.CodeableConcept();
    org.hl7.fhir.Coding maritalStatusCoding = new org.hl7.fhir.Coding();
    MaritalStatus maritalStatus = patient.getMaritalStatus();
    org.hl7.fhir.String maritalStatusDisplay = new org.hl7.fhir.String();
    maritalStatusDisplay.setValue(maritalStatus.getName());
    maritalStatusCoding.setDisplay(maritalStatusDisplay);
    org.hl7.fhir.Code maritalStatusCode = new org.hl7.fhir.Code();
    maritalStatusCode.setValue(maritalStatus.getCode());
    maritalStatusCoding.setCode(maritalStatusCode);
    maritalStatusCodeableConcept.getCoding().add(maritalStatusCoding);
    fhirpatient.setMaritalStatus(maritalStatusCodeableConcept);
    
    org.hl7.fhir.HumanName humanName = new org.hl7.fhir.HumanName();
    List<org.hl7.fhir.String> familyNameList = new ArrayList<org.hl7.fhir.String>();
    org.hl7.fhir.String familyName = new org.hl7.fhir.String();
    familyName.setValue(patient.getLastName());
    familyNameList.add(familyName);
    humanName.getFamily().addAll(familyNameList);
    List<org.hl7.fhir.String> givenNameList = new ArrayList<org.hl7.fhir.String>();
    org.hl7.fhir.String givenName1 = new org.hl7.fhir.String();
    givenName1.setValue(patient.getFirstName());
    givenNameList.add(givenName1);
    org.hl7.fhir.String givenName2 = new org.hl7.fhir.String();
    givenName2.setValue(patient.getMiddleName());
    givenNameList.add(givenName2);
    humanName.getGiven().addAll(givenNameList);
    fhirpatient.getName().add(humanName);
    
    org.hl7.fhir.Contact telecom = new org.hl7.fhir.Contact();
    org.hl7.fhir.String email = new org.hl7.fhir.String();
    email.setValue(patient.getEmail());
    telecom.setValue(email);
    fhirpatient.getTelecom().add(telecom);
    org.hl7.fhir.Contact telecom2 = new org.hl7.fhir.Contact();
    org.hl7.fhir.String primaryPhone = new org.hl7.fhir.String();
    primaryPhone.setValue(patient.getPrimaryPhone());
    telecom2.setValue(primaryPhone);
    fhirpatient.getTelecom().add(telecom2);
    
    org.hl7.fhir.CodeableConcept genderCodeableConcept = new org.hl7.fhir.CodeableConcept();
    org.hl7.fhir.Coding genderStatusCoding = new org.hl7.fhir.Coding();
    org.hl7.fhir.Code genderCode = new org.hl7.fhir.Code();
    org.hl7.fhir.String genderDisplay = new org.hl7.fhir.String();
    genderDisplay.setValue(patient.getGender().getName());
    genderCode.setValue(patient.getGender().getCode());
    genderStatusCoding.setDisplay(genderDisplay);
    genderStatusCoding.setCode(genderCode);
    genderCodeableConcept.getCoding().add(genderStatusCoding);
    fhirpatient.setGender(genderCodeableConcept);
    
    org.hl7.fhir.Address address = new org.hl7.fhir.Address();
    org.hl7.fhir.String line1 = new org.hl7.fhir.String();
    line1.setValue(patient.getStreetAddress1());
    address.getLine().add(line1);
    org.hl7.fhir.String line2 = new org.hl7.fhir.String();
    line2.setValue(patient.getStreetAddress2());
    address.getLine().add(line2);
    org.hl7.fhir.String city = new org.hl7.fhir.String();
    city.setValue(patient.getCity());
    address.setCity(city);
    org.hl7.fhir.String state = new org.hl7.fhir.String();
    state.setValue(patient.getUsState().getName());
    address.setState(state);
    org.hl7.fhir.String zip = new org.hl7.fhir.String();
    zip.setValue(patient.getPostalCode());
    address.setZip(zip);
    org.hl7.fhir.String country = new org.hl7.fhir.String();
    country.setValue(patient.getCountry().getName());
    address.setCountry(country);
    fhirpatient.getAddress().add(address);
    
    return fhirpatient;
    
  }
  
  
   public void updatePatient(org.hl7.fhir.Patient patientFHIR) throws Exception{    
   List<org.hl7.fhir.Identifier> identifierList = patientFHIR.getIdentifier();
   String mrn = null;
     if(identifierList.size() > 0){
      org.hl7.fhir.Identifier identifier = identifierList.get(0);
      if(identifier.getLabel().getValue().equalsIgnoreCase("MRN")){
         mrn = identifier.getValue().getValue();
      }
    }
    if(mrn != null){
      Patient patient = patientDAO.findPatientByMrn(mrn);
      String email = null;
      String primaryPhone = null;
      String secondaryPhone = null;
      List<org.hl7.fhir.Contact> telecomList = patientFHIR.getTelecom();
      if(telecomList.size() > 0){
        org.hl7.fhir.Contact telecom = telecomList.get(0);
        email = telecom.getValue().getValue();
        if(telecomList.size() > 1){
          telecom = telecomList.get(1);
          primaryPhone = telecom.getValue().getValue();
          if(telecomList.size() > 2){
            telecom = telecomList.get(2);
            secondaryPhone = telecom.getValue().getValue();
          }
        }
      }
      String streetAddress1 = null;
      String streetAddress2 = null;
      String city = null;
      USState usState = null;
      String postalCode = null;
      Country country = null;
      List<org.hl7.fhir.Address> addressList = patientFHIR.getAddress();
      if(addressList.size() > 0){
        List<org.hl7.fhir.String> lineList = addressList.get(0).getLine();
        if(lineList.size() > 0){
          streetAddress1 = lineList.get(0).getValue();
          if(lineList.size() > 1){
            streetAddress2 = lineList.get(1).getValue();
          }
        }
        city = addressList.get(0).getCity().getValue();
        usState = patientDAO.findUSStateByName(addressList.get(0).getState().getValue());
        postalCode = addressList.get(0).getZip().getValue();
        country = patientDAO.findCountryByName(addressList.get(0).getCountry().getValue());
      }
      Gender gender = patientDAO.findGenderByCode(patientFHIR.getGender().getCoding().get(0).getCode().getValue());
      MaritalStatus maritalStatus = patientDAO.findMaritalStatusByCode(patientFHIR.getMaritalStatus().getCoding().get(0).getCode().getValue());
      org.hl7.fhir.DateTime birthDate = patientFHIR.getBirthDate();
      if(StringUtils.isNotEmpty(primaryPhone)){
      patient.setPrimaryPhone(primaryPhone);
        }
      if(StringUtils.isNotEmpty(secondaryPhone)){
      patient.setSecondaryPhone(secondaryPhone);
      }
      if(StringUtils.isNotEmpty(streetAddress1)){
      patient.setStreetAddress1(streetAddress1);
      }
      if(StringUtils.isNotEmpty(streetAddress2)){
      patient.setStreetAddress2(streetAddress2);
      }
      if(StringUtils.isNotEmpty(city)){
      patient.setCity(city);
      }
      if(usState != null){
      patient.setUsState(usState);
      }
        if(StringUtils.isNotEmpty(postalCode)){
        patient.setPostalCode(postalCode);
        }
        if(country != null){
        patient.setCountry(country);
        }
        if(gender != null){
        patient.setGender(gender);
        }
        if(maritalStatus != null){
        patient.setMaritalStatus(maritalStatus);
        }
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date dob = dateFormat.parse(birthDate.getValue());
      if(dob != null){
        patient.setDob(dob);
      }
      patientDAO.update(patient);
      String firstName = null;
      String middleName = null;
      String lastName  = null;
      List<org.hl7.fhir.HumanName> humanNameList = patientFHIR.getName();
      if(humanNameList.size() > 0){
        org.hl7.fhir.HumanName humanName = humanNameList.get(0);
        firstName = humanName.getGiven().get(0).getValue();
        if( humanName.getGiven().size() > 1){
          middleName = humanName.getGiven().get(1).getValue();
        }
        lastName  = humanName.getFamily().get(0).getValue();      
      }
      if(patientFHIR.getActive() != null && patientFHIR.getActive().isValue()){
        patient.setStatus(Client.ACTIVE);
      }else{
        patient.setStatus(Client.INACTIVE);
      }
      if(StringUtils.isNotEmpty(firstName)){
      patient.setFirstName(firstName);
      }
      if(StringUtils.isNotEmpty(middleName)){
      patient.setMiddleName(middleName);
      }
      if(StringUtils.isNotEmpty(lastName)){
      patient.setLastName(lastName);
      }
      if(StringUtils.isNotEmpty(email)){
      patient.setEmail(email);
      }
      patientDAO.update(patient);
      DataEncryptor.encryptPatient(patient);
      patientDAO.update(patient);
    }
  }
  
  public PatientFullRecordFHIR getPatientFullRecord(String mrn) throws Exception {
    PatientFullRecordFHIR patientFullRecordFHIR = new PatientFullRecordFHIR();    
    Patient patient = patientDAO.findPatientByMrn(mrn);
    DataEncryptor.decryptPatient(patient);
    org.hl7.fhir.Patient fhirpatient = getPatientFHIR(patient);
    patientFullRecordFHIR.setPatient(fhirpatient);
    List<org.hl7.fhir.SensitivityType> sensitivityTypes = new ArrayList<org.hl7.fhir.SensitivityType>();
    
    org.hl7.fhir.SensitivityType sensitivityTypeAllergyFood = new org.hl7.fhir.SensitivityType();
    sensitivityTypeAllergyFood.setValue(org.hl7.fhir.SensitivityTypeList.ALLERGY);
    sensitivityTypes.add(sensitivityTypeAllergyFood);
    
    org.hl7.fhir.SensitivityType sensitivityTypeAllergyDrug = new org.hl7.fhir.SensitivityType();
    sensitivityTypeAllergyDrug.setValue(org.hl7.fhir.SensitivityTypeList.ALLERGY);
    sensitivityTypes.add(sensitivityTypeAllergyDrug);
    
    org.hl7.fhir.SensitivityType sensitivityTypeAllergyEnv = new org.hl7.fhir.SensitivityType();
    sensitivityTypeAllergyEnv.setValue(org.hl7.fhir.SensitivityTypeList.ALLERGY);
    sensitivityTypes.add(sensitivityTypeAllergyEnv);    
    
    patientFullRecordFHIR.setSensitivityTypes(sensitivityTypes);
    
    List<org.hl7.fhir.MedicationAdministration> medicationAdministrations = new ArrayList<org.hl7.fhir.MedicationAdministration>();

    
    patientFullRecordFHIR.setMedicationAdministrations(medicationAdministrations);
    
    List<org.hl7.fhir.Questionnaire> questionnaires = new ArrayList<org.hl7.fhir.Questionnaire>();

    
    patientFullRecordFHIR.setQuestionnaires(questionnaires);
    
    List<org.hl7.fhir.RelatedPerson> relatedPersons = new ArrayList<org.hl7.fhir.RelatedPerson>();
    
    
    org.hl7.fhir.RelatedPerson motherRelatedPerson = new org.hl7.fhir.RelatedPerson();
    org.hl7.fhir.String motherRelationshipString = new org.hl7.fhir.String();
    motherRelationshipString.setValue("Mother");
    org.hl7.fhir.CodeableConcept motherCodeableConcept = new org.hl7.fhir.CodeableConcept();
    motherCodeableConcept.setText(motherRelationshipString);
    org.hl7.fhir.HumanName motherHumanName = new  org.hl7.fhir.HumanName();
    org.hl7.fhir.String motherNameString = new org.hl7.fhir.String();
    motherHumanName.setText(motherNameString);
    motherRelatedPerson.setName(motherHumanName);
    motherRelatedPerson.setRelationship(motherCodeableConcept);
    
    relatedPersons.add(motherRelatedPerson);
    
    patientFullRecordFHIR.setRelatedPersons(relatedPersons);
    
    
    return patientFullRecordFHIR;
  }

  
  
  public  String importPatients(PatientsFHIR patientsFHIR) throws Exception {    
    List<org.hl7.fhir.Patient> patientFHIRList = patientsFHIR.getPatient();
    for(org.hl7.fhir.Patient patientFHIR : patientFHIRList){
      importPatient(patientFHIR);
    }    
    return null;
  }
  
  private  String importPatient(org.hl7.fhir.Patient patientFHIR) throws Exception {
    Patient patient = new Patient();
    patientDAO.create(patient);
    String email = null;
    String primaryPhone = null;
    String secondaryPhone = null;
    List<org.hl7.fhir.Contact> telecomList = patientFHIR.getTelecom();
    if(telecomList.size() > 0){
      org.hl7.fhir.Contact telecom = telecomList.get(0);
      email = telecom.getValue().getValue();
      if(telecomList.size() > 1){
        telecom = telecomList.get(1);
        primaryPhone = telecom.getValue().getValue();
        if(telecomList.size() > 2){
          telecom = telecomList.get(2);
          secondaryPhone = telecom.getValue().getValue();
        }
      }
    }
    String streetAddress1 = null;
    String streetAddress2 = null;
    String city = null;
    USState usState = null;
    String postalCode = null;
    Country country = null;
    List<org.hl7.fhir.Address> addressList = patientFHIR.getAddress();
    if(addressList.size() > 0){
      List<org.hl7.fhir.String> lineList = addressList.get(0).getLine();
      if(lineList.size() > 0){
        streetAddress1 = lineList.get(0).getValue();
        if(lineList.size() > 1){
          streetAddress2 = lineList.get(1).getValue();
        }
      }
      city = addressList.get(0).getCity().getValue();
      usState = patientDAO.findUSStateByName(addressList.get(0).getState().getValue());
      postalCode = addressList.get(0).getZip().getValue();
      country = patientDAO.findCountryByName(addressList.get(0).getCountry().getValue());
    }
    Gender gender = patientDAO.findGenderByCode(patientFHIR.getGender().getCoding().get(0).getCode().getValue());
    MaritalStatus maritalStatus = patientDAO.findMaritalStatusByCode(patientFHIR.getMaritalStatus().getCoding().get(0).getCode().getValue());
    org.hl7.fhir.DateTime birthDate = patientFHIR.getBirthDate();
    patient.setPrimaryPhone(primaryPhone);
    patient.setSecondaryPhone(secondaryPhone);
    patient.setStreetAddress1(streetAddress1);
    patient.setStreetAddress2(streetAddress2);
    patient.setCity(city);
    patient.setUsState(usState);
    patient.setPostalCode(postalCode);
    patient.setCountry(country);
    patient.setGender(gender);
    patient.setMaritalStatus(maritalStatus);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dob = dateFormat.parse(birthDate.getValue());
    patient.setDob(dob);
    patientDAO.create(patient);
    String firstName = null;
    String middleName = null;
    String lastName  = null;
    List<org.hl7.fhir.HumanName> humanNameList = patientFHIR.getName();
    if(humanNameList.size() > 0){
      org.hl7.fhir.HumanName humanName = humanNameList.get(0);
      firstName = humanName.getGiven().get(0).getValue();
      if( humanName.getGiven().size() > 1){
        middleName = humanName.getGiven().get(1).getValue();
      }
      lastName  = humanName.getFamily().get(0).getValue();      
    }
      if(patientFHIR.getActive() != null && patientFHIR.getActive().isValue()){
        patient.setStatus(Client.ACTIVE);
      }else{
        patient.setStatus(Client.INACTIVE);
      }
    String mrn = null;
    List<org.hl7.fhir.Identifier> identifierList = patientFHIR.getIdentifier();
    if(identifierList.size() > 0){
      org.hl7.fhir.Identifier identifier = identifierList.get(0);
      if(identifier.getLabel().getValue().equalsIgnoreCase("MRN")){
        mrn = identifier.getValue().getValue();
      }
    }
    patient.setMrn(mrn);
    patient.setFirstName(firstName);
    patient.setMiddleName(middleName);
    patient.setLastName(lastName);
    patient.setEmail(email);
    patient.setPassword("not a password");
    patientDAO.create(patient);
    
    DataEncryptor.encryptPatient(patient); 
    patientDAO.update(patient);
    return null;
  }
  
  public org.hl7.fhir.Encounter buildPatientEncounter(AppDTO dto) throws Exception {
    Patient patient = patientService.getPatient(dto.id);
    DataEncryptor.decryptPatient(patient);
    
    org.hl7.fhir.Encounter encounter = new org.hl7.fhir.Encounter();
    org.hl7.fhir.Identifier identifier = new org.hl7.fhir.Identifier();
    org.hl7.fhir.IdentifierUse identifierUse = new  org.hl7.fhir.IdentifierUse();
    identifierUse.setId(org.hl7.fhir.IdentifierUseList.TEMP.value());
    identifier.setUse(identifierUse);
    org.hl7.fhir.String mrn = new org.hl7.fhir.String();
    mrn.setValue("Sara's encounter on March eleventh 2013");
    identifier.setLabel(mrn);
    org.hl7.fhir.String mrnValue = new org.hl7.fhir.String();
    mrnValue.setValue("Encounter_Sara_20130311");
    identifier.setValue(mrnValue);
    encounter.getIdentifier().add(identifier);
    org.hl7.fhir.String reasonValue = new org.hl7.fhir.String();
    org.hl7.fhir.CodeableConcept reasonCodeableConcept = new org.hl7.fhir.CodeableConcept();
    reasonCodeableConcept.setText(reasonValue);
    encounter.setReason(reasonCodeableConcept);
    org.hl7.fhir.String subjectValue = new org.hl7.fhir.String();
    subjectValue.setValue(patient.getFirstName() + " " + patient.getLastName());
    org.hl7.fhir.ResourceReference reRef = new org.hl7.fhir.ResourceReference();
    reRef.setDisplay(subjectValue);
    encounter.setSubject(reRef);
    
    org.hl7.fhir.String indValue = new org.hl7.fhir.String();
    org.hl7.fhir.ResourceReference indRef = new org.hl7.fhir.ResourceReference();
    indRef.setDisplay(indValue);
    org.hl7.fhir.EncounterParticipant encP = new org.hl7.fhir.EncounterParticipant();
    encP.setIndividual(indRef);
    encounter.getParticipant().add(encP);
    return encounter;
  }
  
  
  public PatientsFHIR buildPatientResource(List<Patient> patients) throws Exception {
    PatientsFHIR patientsFHIR = new PatientsFHIR();
    
    for(int i = 0; i < patients.size(); i++){
      DataEncryptor.decryptPatient(patients.get(i));
      org.hl7.fhir.Patient fhirpatient = new org.hl7.fhir.Patient();
      org.hl7.fhir.DateTime birthDate = new org.hl7.fhir.DateTime();
      birthDate.setValue(patients.get(i).getDob().toString());
        
      fhirpatient.setBirthDate(birthDate);
        
      org.hl7.fhir.Identifier identifier = new org.hl7.fhir.Identifier();
      org.hl7.fhir.IdentifierUse identifierUse = new  org.hl7.fhir.IdentifierUse();
      identifierUse.setId(org.hl7.fhir.IdentifierUseList.USUAL.value());
      identifier.setUse(identifierUse);
      org.hl7.fhir.String mrn = new org.hl7.fhir.String();
      mrn.setValue("MRN");
      identifier.setLabel(mrn);
      org.hl7.fhir.String mrnValue = new org.hl7.fhir.String();
      mrnValue.setValue(patients.get(i).getMrn());
      identifier.setValue(mrnValue);
      fhirpatient.getIdentifier().add(identifier);
      
      org.hl7.fhir.CodeableConcept maritalStatusCodeableConcept = new org.hl7.fhir.CodeableConcept();
      org.hl7.fhir.Coding maritalStatusCoding = new org.hl7.fhir.Coding();
      org.hl7.fhir.String maritalStatusDisplay = new org.hl7.fhir.String();
      org.hl7.fhir.Code maritalStatusCode = new org.hl7.fhir.Code();
      MaritalStatus maritalStatus = null;
      if(patients.get(i) != null && patients.get(i).getMaritalStatus() != null){
    	  maritalStatus = patients.get(i).getMaritalStatus();
    	  maritalStatusDisplay.setValue(maritalStatus.getName());
    	  maritalStatusCoding.setDisplay(maritalStatusDisplay);
    	  maritalStatusCode.setValue(maritalStatus.getCode());
      }
      maritalStatusCoding.setCode(maritalStatusCode);
      maritalStatusCodeableConcept.getCoding().add(maritalStatusCoding);
      fhirpatient.setMaritalStatus(maritalStatusCodeableConcept);
      
      org.hl7.fhir.HumanName humanName = new org.hl7.fhir.HumanName();
      List<org.hl7.fhir.String> familyNameList = new ArrayList<org.hl7.fhir.String>();
      org.hl7.fhir.String familyName = new org.hl7.fhir.String();
      familyName.setValue(patients.get(i).getLastName());
      familyNameList.add(familyName);
      humanName.getFamily().addAll(familyNameList);
      List<org.hl7.fhir.String> givenNameList = new ArrayList<org.hl7.fhir.String>();
      org.hl7.fhir.String givenName1 = new org.hl7.fhir.String();
      givenName1.setValue(patients.get(i).getFirstName());
      givenNameList.add(givenName1);
      org.hl7.fhir.String givenName2 = new org.hl7.fhir.String();
      givenName2.setValue(patients.get(i).getMiddleName());
      givenNameList.add(givenName2);
      humanName.getGiven().addAll(givenNameList);
      fhirpatient.getName().add(humanName);
      
      org.hl7.fhir.Contact telecom = new org.hl7.fhir.Contact();
      org.hl7.fhir.String email = new org.hl7.fhir.String();
      email.setValue(patients.get(i).getEmail());
      telecom.setValue(email);
      fhirpatient.getTelecom().add(telecom);
      org.hl7.fhir.Contact telecom2 = new org.hl7.fhir.Contact();
      org.hl7.fhir.String primaryPhone = new org.hl7.fhir.String();
      primaryPhone.setValue(patients.get(i).getPrimaryPhone());
      telecom2.setValue(primaryPhone);
      fhirpatient.getTelecom().add(telecom2);
      
      org.hl7.fhir.CodeableConcept genderCodeableConcept = new org.hl7.fhir.CodeableConcept();
      org.hl7.fhir.Coding genderStatusCoding = new org.hl7.fhir.Coding();
      org.hl7.fhir.Code genderCode = new org.hl7.fhir.Code();
      org.hl7.fhir.String genderDisplay = new org.hl7.fhir.String();
      genderDisplay.setValue(patients.get(i).getGender().getName());
      genderCode.setValue(patients.get(i).getGender().getCode());
      genderStatusCoding.setDisplay(genderDisplay);
      genderStatusCoding.setCode(genderCode);
      genderCodeableConcept.getCoding().add(genderStatusCoding);
      fhirpatient.setGender(genderCodeableConcept);
      
      org.hl7.fhir.Address address = new org.hl7.fhir.Address();
      org.hl7.fhir.String line = new org.hl7.fhir.String();
      line.setValue(patients.get(i).getStreetAddress1());
      address.getLine().add(line);
      org.hl7.fhir.String city = new org.hl7.fhir.String();
      city.setValue(patients.get(i).getCity());
      address.setCity(city);
      org.hl7.fhir.String state = new org.hl7.fhir.String();
      state.setValue(patients.get(i).getUsState().getName());
      address.setState(state);
      org.hl7.fhir.String zip = new org.hl7.fhir.String();
      zip.setValue(patients.get(i).getPostalCode());
      address.setZip(zip);
      org.hl7.fhir.String country = new org.hl7.fhir.String();
      if(patients.get(i) != null && patients.get(i).getCountry() != null){
    	  country.setValue(patients.get(i).getCountry().getName());
      }
      address.setCountry(country);  
      fhirpatient.getAddress().add(address);
      org.hl7.fhir.Integer numChildren = new org.hl7.fhir.Integer();
      fhirpatient.setMultipleBirthInteger(numChildren);
      patientsFHIR.getPatient().add(fhirpatient);
    }
    return patientsFHIR;
  }
  
  


}
