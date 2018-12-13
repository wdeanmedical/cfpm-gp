package com.wdeanmedical.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;

public class DataEncryptor {

  private static final String ALGORITHM = "AES";
  private static byte[] encryptionKey;

  public static void setEncryptionKey(String key) throws Exception {
    encryptionKey = key.getBytes("UTF-8");
  }


  public static String encrypt(String valueToEnc) throws Exception {
    String encryptedValue = null;

    if (valueToEnc != null) {
      Key key = generateKey();
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] encValue = c.doFinal(valueToEnc.getBytes());
      encryptedValue = new Base64Encoder().encode(encValue);
    } 
    else {
      return "";
    }
    return encryptedValue;
  }
  
  
  

  public static String decrypt(String encryptedValue) throws Exception {
    String decryptedValue = null;

    if (encryptedValue != null) {
      Key key = generateKey();
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.DECRYPT_MODE, key);
      byte[] decordedValue = new Base64Encoder().decode(encryptedValue);
      byte[] decValue = c.doFinal(decordedValue);
      decryptedValue = new String(decValue);
    } 
    else {
      return "";
    }
    return decryptedValue;
  }
  
  

  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(encryptionKey, ALGORITHM);
    return key;
  }
  
  
    
  public static void encryptGuardian(Guardian guardian) throws Exception { 
    if (guardian == null || guardian.isEncrypted()) {
      return;
    }
    if (guardian.getCustomerKey() != null) { guardian.setCustomerKey(encrypt(guardian.getCustomerKey()));}
    if (guardian.getEmail() != null) { guardian.setEmail(encrypt(guardian.getEmail()));}
    if (guardian.getUsername() != null) { guardian.setUsername(encrypt(guardian.getUsername()));}
    if (guardian.getFirstName() != null) { guardian.setFirstName(encrypt(guardian.getFirstName()));}
    if (guardian.getMiddleName() != null) { guardian.setMiddleName(encrypt(guardian.getMiddleName()));}
    if (guardian.getLastName() != null) { guardian.setLastName(encrypt(guardian.getLastName()));}
    if (guardian.getStreetAddress1() != null) { guardian.setStreetAddress1(encrypt(guardian.getStreetAddress1()));}
    if (guardian.getSecondaryPhone() != null) { guardian.setSecondaryPhone(encrypt(guardian.getSecondaryPhone()));}
    if (guardian.getPostalCode() != null) { guardian.setPostalCode(encrypt(guardian.getPostalCode()));}
    if (guardian.getCity() != null) { guardian.setCity(encrypt(guardian.getCity()));}
    if (guardian.getPrimaryPhone() != null) { guardian.setPrimaryPhone(encrypt(guardian.getPrimaryPhone()));}
    guardian.setEncrypted(true);
  } 
  
  
  
  public static void encryptPatient(Patient patient) throws Exception { 
    if (patient == null || patient.isEncrypted()) {
      return;
    }
    if (patient.getCustomerKey() != null) { patient.setCustomerKey(encrypt(patient.getCustomerKey()));}
    if (patient.getUsername() != null) { patient.setUsername(encrypt(patient.getUsername()));}
    if (patient.getFirstName() != null) { patient.setFirstName(encrypt(patient.getFirstName()));}
    if (patient.getMiddleName() != null) { patient.setMiddleName(encrypt(patient.getMiddleName()));}
    if (patient.getLastName() != null) { patient.setLastName(encrypt(patient.getLastName()));}
    if (patient.getAdditionalName() != null) { patient.setAdditionalName(encrypt(patient.getAdditionalName()));}
    if (patient.getEmail() != null) { patient.setEmail(encrypt(patient.getEmail()));}
    if (patient.getGovtId() != null) { patient.setGovtId(encrypt(patient.getGovtId()));}
    if (patient.getDriversLicense() != null) { patient.setDriversLicense(encrypt(patient.getDriversLicense()));}
    if (patient.getPrimaryPhone() != null) { patient.setPrimaryPhone(encrypt(patient.getPrimaryPhone()));}
    if (patient.getSecondaryPhone() != null) { patient.setSecondaryPhone(encrypt(patient.getSecondaryPhone()));}
    if (patient.getStreetAddress1() != null) { patient.setStreetAddress1(encrypt(patient.getStreetAddress1()));}
    if (patient.getStreetAddress2() != null) { patient.setStreetAddress2(encrypt(patient.getStreetAddress2()));}
    if (patient.getCity() != null) { patient.setCity(encrypt(patient.getCity()));}
    if (patient.getPostalCode() != null) { patient.setPostalCode(encrypt(patient.getPostalCode()));}
    if (patient.getEmployer() != null) { patient.setEmployer(encrypt(patient.getEmployer()));}
    if (patient.getOccupation() != null) { patient.setOccupation(encrypt(patient.getOccupation()));}
    if (patient.getSchoolName() != null) { patient.setSchoolName(encrypt(patient.getSchoolName()));}
    if (patient.getPreferredName() != null) { patient.setPreferredName(encrypt(patient.getPreferredName()));}
    if (patient.getInsuranceCarrier() != null) { patient.setInsuranceCarrier(encrypt(patient.getInsuranceCarrier()));}
    if (patient.getInsuredName() != null) { patient.setInsuredName(encrypt(patient.getInsuredName()));}
    if (patient.getMemberNumber() != null) { patient.setMemberNumber(encrypt(patient.getMemberNumber()));}
    if (patient.getGroupNumber() != null) { patient.setGroupNumber(encrypt(patient.getGroupNumber()));}    
    patient.setEncrypted(true);
  }  
 
  
  
  public static void decryptGuardian(Guardian guardian) throws Exception { 
    if (guardian == null || guardian.isEncrypted() == false) {
      return;
    }
    if (guardian.getCustomerKey() != null) { guardian.setCustomerKey(decrypt(guardian.getCustomerKey()));}
    if (guardian.getEmail() != null) { guardian.setEmail(decrypt(guardian.getEmail()));}
    if (guardian.getUsername() != null) { guardian.setUsername(decrypt(guardian.getUsername()));}
    if (guardian.getFirstName() != null) { guardian.setFirstName(decrypt(guardian.getFirstName()));}
    if (guardian.getMiddleName() != null) { guardian.setMiddleName(decrypt(guardian.getMiddleName()));}
    if (guardian.getLastName() != null) { guardian.setLastName(decrypt(guardian.getLastName()));}
    if (guardian.getStreetAddress1() != null) { guardian.setStreetAddress1(decrypt(guardian.getStreetAddress1()));}
    if (guardian.getPostalCode() != null) { guardian.setPostalCode(decrypt(guardian.getPostalCode()));}
    if (guardian.getCity() != null) { guardian.setCity(decrypt(guardian.getCity()));}
    if (guardian.getPrimaryPhone() != null) { guardian.setPrimaryPhone(decrypt(guardian.getPrimaryPhone()));}
    if (guardian.getSecondaryPhone() != null) { guardian.setSecondaryPhone(decrypt(guardian.getSecondaryPhone()));}
      guardian.setEncrypted(false);
    } 
  
  
  public static void decryptPatient(Patient patient) throws Exception { 
    if (patient == null || patient.isEncrypted() == false) {
      return;
    }
    if (patient.getCustomerKey() != null) { patient.setCustomerKey(decrypt(patient.getCustomerKey()));}
    if (patient.getUsername() != null) { patient.setUsername(decrypt(patient.getUsername()));}
    if (patient.getFirstName() != null) { patient.setFirstName(decrypt(patient.getFirstName()));}
    if (patient.getMiddleName() != null) { patient.setMiddleName(decrypt(patient.getMiddleName()));}
    if (patient.getLastName() != null) { patient.setLastName(decrypt(patient.getLastName()));}
    if (patient.getAdditionalName() != null) { patient.setAdditionalName(decrypt(patient.getAdditionalName()));}
    if (patient.getEmail() != null) { patient.setEmail(decrypt(patient.getEmail()));}
    if (patient.getGovtId() != null) { patient.setGovtId(decrypt(patient.getGovtId()));}
    if (patient.getDriversLicense() != null) { patient.setDriversLicense(decrypt(patient.getDriversLicense()));}
    if (patient.getPrimaryPhone() != null) { patient.setPrimaryPhone(decrypt(patient.getPrimaryPhone()));}
    if (patient.getSecondaryPhone() != null) { patient.setSecondaryPhone(decrypt(patient.getSecondaryPhone()));}
    if (patient.getStreetAddress1() != null) { patient.setStreetAddress1(decrypt(patient.getStreetAddress1()));}
    if (patient.getStreetAddress2() != null) { patient.setStreetAddress2(decrypt(patient.getStreetAddress2()));}
    if (patient.getCity() != null) { patient.setCity(decrypt(patient.getCity()));}
    if (patient.getPostalCode() != null) { patient.setPostalCode(decrypt(patient.getPostalCode()));}
    if (patient.getEmployer() != null) { patient.setEmployer(decrypt(patient.getEmployer()));}
    if (patient.getOccupation() != null) { patient.setOccupation(decrypt(patient.getOccupation()));}
    if (patient.getSchoolName() != null) { patient.setSchoolName(decrypt(patient.getSchoolName()));}
    if (patient.getPreferredName() != null) { patient.setPreferredName(decrypt(patient.getPreferredName()));}
    if (patient.getInsuranceCarrier() != null) { patient.setInsuranceCarrier(decrypt(patient.getInsuranceCarrier()));}
    if (patient.getInsuredName() != null) { patient.setInsuredName(decrypt(patient.getInsuredName()));}
    if (patient.getMemberNumber() != null) { patient.setMemberNumber(decrypt(patient.getMemberNumber()));}
    if (patient.getGroupNumber() != null) { patient.setGroupNumber(decrypt(patient.getGroupNumber()));}
    patient.setEncrypted(false);
  } 
  
  
}
