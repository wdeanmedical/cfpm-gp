package com.wdeanmedical.dto; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.wdeanmedical.entity.Facility;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.NetworkMarketingSource;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.IError;
import com.wdeanmedical.model.Criterion;


public class AppDTO {

  public String action;
	public List<Criterion> criteria;
  public Integer altClinicianId;
  public Boolean authenticated = true;
  public Boolean byPatientId;
  public Object client;
  public Properties clientProperties;
  public Integer clinicianId;
  public String className;
  public String clientType;
  public String content;
  public String copayMethod;
  public Boolean createIfNull = new Boolean(false);
  public String date;
  public Integer departmentId;
  public String desc;
  public String description;
  public String direction;
  public String dx;
  public String email;
  public Integer encounterId;
  public String endDate;
  public String errorMsg;
  public String evalMode;
  public String evalType;
  public Facility facility;
  public String filename;
  public Integer formId;
  public String formClassName;
  public Integer id;
  public Boolean markAsRead = new Boolean(false);
  public Integer messageType;
  public Integer minPriority;
  public String mode;
  public String module;
  public String name;
  public NetworkMarketingSource network;
  public Object object;
  public Boolean override;
  public String password;
  public Integer parentId;
  public String parentName;
  public String path;
  public Patient patient;
  public Integer patientFormId;
  public Integer patientId;
  public Properties practiceClientProperties;
  public Integer previousId;
  public Integer priority;
  public String recoveryCode;
  public Boolean result;
  public Integer returnCode;
  public String searchText;
  public String service;
  public String sessionId;
  public String siteKey;
  public Integer size;
  public Properties specialtyClientProperties;
  public Integer start;
  public String startDate;
  public String subject;
  public Boolean success;
  public String title;
  public Integer total;
  public String totalTime;
  public Integer txNoteId;
  public String type;
  public String updateProperty;
  public String updatePropertyValue;
  public Object updatePreviousValue;
  public String updatePropertyFieldType;
  public Boolean updateSessionId;
  public User user;
  public Integer userId;
  public String userType;
  public String username;
  public Integer guardianId;
  public String errorType;

  public String visitReason;
  
  public List<?> list;
 
  public Map<String,List<?>> appLists = new HashMap<String,List<?>>();
  public Map<String,List<?>> dashboard = new HashMap<String,List<?>>();
  public Map<String,Set<?>> patientSearchTypeAheads = new HashMap<String,Set<?>>();
  public Map<String,List<?>> staticLists = new HashMap<String,List<?>>();
  public Guardian guardian;
  public String parentIdName;
  
  public void setError(IError error) {
    this.result = false;
    this.errorMsg = error.getErrorMsg();
    this.returnCode = error.getErrorCode();
    this.errorType = error.getErrorType();
  }

   public Integer getPatientId() {
	   return patientId;
   }

public Integer getClinicianId() {
	return clinicianId;
}
   
   

}
