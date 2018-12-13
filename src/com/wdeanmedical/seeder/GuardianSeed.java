package com.wdeanmedical.seeder;

import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.factory.GuardianFactory;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.service.PatientService;

public class GuardianSeed {
  private GuardianFactory guardianFactory;
  private PatientDAO patientDAO;

  public GuardianSeed(AppDAO appDAO, PatientDAO patientDAO) {
    this.patientDAO=patientDAO;
    this.guardianFactory= new GuardianFactory(appDAO);
  }

  public void addGuardian(PatientDTO dto) throws Exception {
    Guardian guardian = guardianFactory.createNewGuardian(dto);
    if(guardian==null) {
      return;
    }
    PatientService.addPatientPatientGuardian(patientDAO, dto.patient.getId(), guardian, true);
    dto.guardian=guardian;
  }
}
