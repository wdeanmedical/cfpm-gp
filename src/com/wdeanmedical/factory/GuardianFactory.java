package com.wdeanmedical.factory;

import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.service.PatientService;
import com.wdeanmedical.util.DataEncryptor;

public class GuardianFactory {

  private AppDAO appDAO;

  public GuardianFactory(AppDAO appDAO){
    this.appDAO = appDAO;
  }
  public Guardian createNewGuardian(PatientDTO dto) throws Exception {
    String encryptedEmail = DataEncryptor.encrypt(dto.email);
    
    if (appDAO.checkEmail(encryptedEmail, Client.GUARDIAN) == false) {
      dto.result = false; 
      dto.errorMsg = "Email already in system";
      dto.returnCode = PatientService.RETURN_CODE_DUP_EMAIL;
      return null;
    }
    
    Guardian guardian = new Guardian();
    guardian.setEncrypted(false);
    guardian.setClientType(Client.GUARDIAN);
    guardian.setPrimaryPhone(dto.primaryPhone);
    guardian.setStreetAddress1(dto.address1);
    guardian.setCity(dto.city);
    guardian.setUsState(appDAO.findById(USState.class, dto.usState));
    guardian.setPostalCode(dto.postalCode);
    guardian.setPasswordCreated(false);
    guardian.setFirstName(dto.firstName);
    guardian.setMiddleName(dto.middleName);
    guardian.setLastName(dto.lastName);
    guardian.setPassword("not a password");
    guardian.setStatus(Client.ACTIVE);
    guardian.setEmail(dto.email);
    guardian.setUsername(dto.email);
    DataEncryptor.encryptGuardian(guardian);

    appDAO.create(guardian);
    return guardian;
  }
}
