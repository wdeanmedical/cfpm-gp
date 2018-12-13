package com.wdeanmedical.factory;

import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Ethnicity;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.MaritalStatus;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.PatientIntake;
import com.wdeanmedical.entity.Race;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.service.AppService;
import com.wdeanmedical.service.PatientService;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.Uploader;

public class PatientFactory {
  public static String DATE_FORMAT="MM/dd/yyyy";

  private PatientDAO dao;

  public PatientFactory(PatientDAO appDAO){
    this.dao = appDAO;
  }
  public Patient createNewPatient(PatientDTO dto) throws Exception {
    dto.patientId = null;
    Guardian patientGuardian = null;

    if (dto.guardianId != null) {
      patientGuardian = dao.findById(Guardian.class, dto.guardianId);
    }

    if (patientGuardian == null) {
      String encryptedEmail = DataEncryptor.encrypt(dto.email);
      if (dao.checkEmail(encryptedEmail) == false) {
        dto.result = false;
        dto.errorMsg = "Email already in system";
        dto.returnCode = PatientService.RETURN_CODE_DUP_EMAIL;
        return null;
      }
    }

    Patient patient = new Patient();
    
    patient.setEncrypted(false);
    patient.setClientType(Client.PATIENT);
    patient.setMrn(dto.mrn);
    patient.setGender(dao.findById(Gender.class, dto.genderId));
    Date dob;
    SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT);
    try {
      dob = sdf.parse(dto.dob);
    } catch (ParseException pe) {
      dob = null;
    }
    patient.setDob(dob);
    Integer raceId;
    try {
      raceId = new Integer(dto.race);
    } catch (NumberFormatException nfe) {
      raceId = null;
    }
    patient.setRace(dao.findById(Race.class, raceId));
    Integer assignedClinicianId;
    try {
      assignedClinicianId = new Integer(dto.assignedClinicianId);
    } catch (NumberFormatException nfe) {
      assignedClinicianId = null;
    }
    patient.setAssignedClinicianId(assignedClinicianId);
    Integer ethnicityId;
    try {
      ethnicityId = new Integer(dto.ethnicity);
    } catch (NumberFormatException nfe) {
      ethnicityId = null;
    }
    patient.setEthnicity(dao.findById(Ethnicity.class, ethnicityId));
    Integer maritalStatusId;
    try {
      maritalStatusId = new Integer(dto.maritalStatus);
    } catch (NumberFormatException nfe) {
      maritalStatusId = null;
    }
    patient.setMaritalStatus(dao.findById(MaritalStatus.class, maritalStatusId));
    patient.setPrimaryPhone(dto.primaryPhone);
    patient.setSecondaryPhone(dto.secondaryPhone);
    patient.setStreetAddress1(dto.address1);
    patient.setStreetAddress2(dto.address2);
    patient.setCity(dto.city);
    patient.setUsState(dao.findById(USState.class, dto.usState));
    patient.setPostalCode(dto.postalCode);
    patient.setOccupation(dto.occupation);
    patient.setEmploymentStatus(dto.employed);
    patient.setEmployer(dto.employer);
    patient.setSchoolStatus(dto.school);
    patient.setSchoolName(dto.schoolName);
    patient.setPreferredName(dto.preferredName);
    patient.setPasswordCreated(false);
    patient.setIntakeClosed(false);
    patient.setFirstName(dto.firstName);
    patient.setMiddleName(dto.middleName);
    patient.setLastName(dto.lastName);
    patient.setPassword("not a password");
    patient.setPrepaymentAmount(dto.prepaymentAmount);
    patient.setStatus(Client.ACTIVE);
    patient.setGovtId(dto.ssn);
    patient.setEmail(dto.email);
    patient.setDriversLicense(dto.driversLicense);
    patient.setUsername(dto.email);

    patient.setInsuranceCarrier(dto.insuranceCarrier);
    patient.setInsuredName(dto.insuredName);
    patient.setGroupNumber(dto.groupNumber);
    patient.setMemberNumber(dto.memberNumber);

    patient.setPrograms(dto.programs);

    DataEncryptor.encryptPatient(patient);

    if (StringUtils.isEmpty(dto.profileImageTempPath)) {
      patient.setProfileImagePath(Core.headshotPlaceholder);
    } else {
      patient.setProfileImagePath(dto.profileImageTempPath);
    }

    patient.setActivationCode(AppService.newActivationCode());

    Integer createClinicianId = User.ANY_CLINICIAN;
    if (dto.createClinicianId != null) {
      createClinicianId = dto.createClinicianId;
    }
    
    patient.setAssignedClinicianId(createClinicianId);
    
    dao.create(patient);
      
    dto.id = patient.getId();

    if (StringUtils.isEmpty(dto.profileImageTempPath)) {
      // copy default file to patient dir
      File destDir = Paths.get(Core.filesHome, Core.patientDirPath, dto.id.toString()).toFile();
      destDir.mkdirs();
      File srcFile = Paths.get(Core.appBaseDir, "assets/images", Core.headshotPlaceholder).toFile();
      FileUtils.copyFileToDirectory(srcFile, destDir);
    } else {
      Uploader.processPatientUpload(dto);
    }

    PatientIntake patientIntake = new PatientIntake();
    patientIntake.setPatientId(patient.getId());
    dao.create(patientIntake);

    PatientClinician pc = new PatientClinician();
    pc.setPatient(patient);
    User anyClinician = dao.findById(User.class, createClinicianId);
    pc.setClinician(anyClinician);
    dao.create(pc);
    
    if (patientGuardian != null) {
     PatientService.setPatientPrimaryGuardian(dao, patient.getId(), patientGuardian);
    }
    
    dto.patientId = patient.getId();
    return patient;
  }
}
