package com.wdeanmedical.dto; 

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wdeanmedical.entity.SalesLead;
import com.wdeanmedical.entity.SalesLeadTask;

public class LeadMgmtDTO extends AppDTO {
	
  public String addressType; 
  public String addressTypeId; 
  public String altAddressType; 
  public String altAddressTypeId; 
  public String altCity;
  public String altPostalCode;
  public String altStreetAddress1;
  public String altUSState;
  public String altUSStateId;
  public String ageRange;
  public String ageRangeId;
  public String bestTimeToContact;  
  public String callStatus;
  public String callStatusId;
  public String city;
  public String clinicianIds;
  public Integer creatorId;
  public String dob;
  public String driversLicense;
  public String dueDate;
  public String emailStatus;
  public String emailStatusId;
  public String facilityId;
  public String firstName;
  public String forms;
  public String fullName;
  public String gender;
  public String genderId;
  public String goal;
  public String goalId;
  public String lastName;
  public String middleName;
  public String networkMarketingSource;
  public String networkMarketingSourceId;
  public String note;
  public String notes;
  public String occupation;
  public String postalCode;
  public String prepaymentAmount;
  public String primaryPhone;
  public String salesAgentIdsString;
  public SalesLead salesLead; 
  public SalesLeadTask salesLeadTask; 
  public String secondaryPhone;
  public Boolean sendPortalInvite;
  public String sourceId;
  public String source;
  public String sourceType;
  public String sourceTypeId;
  public String ssn;
  public String stage;
  public String stageId;
  public String status;
  public String statusId;
  public String streetAddress1;
  public Boolean timeSpecified;
  public String userIds;
  public String usState;
  public String usStateId;
  
  public Map<String, Set<String>> salesLeadCitySearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadFirstNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadMiddleNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadLastNameSearchTypeAheads = new HashMap<String, Set<String>>();
  public Map<String, Set<String>> salesLeadUSStateSearchTypeAheads = new HashMap<String, Set<String>>(); 
}
