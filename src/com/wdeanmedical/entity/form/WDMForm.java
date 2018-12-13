package com.wdeanmedical.entity.form;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Ethnicity;
import com.wdeanmedical.entity.Gender;
import com.wdeanmedical.entity.MaritalStatus;
import com.wdeanmedical.entity.Race;
import com.wdeanmedical.entity.USState;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.interfaces.IFilterable;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.service.AppService;

@MappedSuperclass()
public class WDMForm extends BaseEntity implements IFilterable {

  public static final String CLOSE = "close";
  public static final String DELETE = "delete";
  public static final String OPEN = "open";

  public static String[] NON_STRING_FORM_FIELD_TYPES = new String[] {
    "boolean",
    "date",
    "ethnicity",
    "float",
    "gender",
    "integer",
    "maritalStatus",
    "object",
    "race",
    "usState"
  };
  
  
  private Integer patientId;
  private Integer clinicianId;
  private Boolean closed = new Boolean(false);
  private Boolean deleted = new Boolean(false);
  private Date date = new Date();
  protected String name;
  private PracticeForm practiceForm;
  private User clinician;
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }

  @Column(name = "clinician_id")
  public Integer getClinicianId() { return clinicianId; }
  public void setClinicianId(Integer clinicianId) { this.clinicianId = clinicianId; }

  @JoinColumn(name = "clinician_id", referencedColumnName = "id", insertable=false, updatable=false)
  @ManyToOne(optional = true)
  public User getClinician() { return clinician; }
  public void setClinician(User clinician) { this.clinician = clinician; }

  @Column(name = "closed")
  public Boolean getClosed() { return closed; }
  public void setClosed(Boolean closed) { this.closed = closed; }
  
  @Column(name = "deleted")
  public Boolean getDeleted() { return deleted; }
  public void setDeleted(Boolean deleted) { this.deleted = deleted; }

  @Column(name = "date")
  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }
  
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @Transient  
  public PracticeForm getPracticeForm() { return practiceForm; }
  public void setPracticeForm(PracticeForm practiceForm) { this.practiceForm = practiceForm; }
  
  
  public static boolean isNonStringField(String fieldType) throws Exception {
    return Arrays.asList(NON_STRING_FORM_FIELD_TYPES).contains(fieldType);
  } 
  
  
  
  public static boolean isPHI(String property, Class<?>  fieldClass) throws Exception {
    boolean result = false;
    Field field = null;
    try {
      field = fieldClass.getDeclaredField("PHI_FIELDS");
    } catch(NoSuchFieldException e) {  
    }
    if (field != null) {
      return Arrays.asList((String[])field.get(null)).contains(property);
    }
    return result;
  }
  
  public static Object processNonStringField(String formSectionPropertyName, String updatePropertyValue, 
    String updatePropertyFieldType, AppDAO appDAO) throws Exception {
    Object newValue = null;
    
    if ("boolean".equals(updatePropertyFieldType))  {
      if (updatePropertyValue == null) {
        newValue = null; 
      }
      else {
        Boolean val; try { val = new Boolean(updatePropertyValue); } catch (NumberFormatException nfe) {val = null;}
        newValue = val;
      }
    }
    else if ("user".equals(updatePropertyFieldType))  {
      Integer userId; try { userId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {userId = null;}
      newValue = appDAO.findById(User.class, userId);
    }
    else if ("date".equals(updatePropertyFieldType))  {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      Date date; try { date = sdf.parse(updatePropertyValue); } catch (ParseException pe) {date = null;}
      newValue = date;
    }
    else if ("ethnicity".equals(updatePropertyFieldType))  {
      Integer ethnicityId; try { ethnicityId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {ethnicityId = null;}
      newValue = appDAO.findById(Ethnicity.class, ethnicityId);
    }
    else if ("integer".equals(updatePropertyFieldType))  {
      Integer intValue; try { intValue = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {intValue = null;}
      newValue = intValue;
    }
    else if ("float".equals(updatePropertyFieldType))  {
      Float floatValue; try { floatValue = new Float(updatePropertyValue); } catch (NumberFormatException nfe) {floatValue = null;}
      newValue = floatValue;
    }
    else if ("gender".equals(updatePropertyFieldType))  {
      Integer genderId; try { genderId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {genderId = null;}
      newValue = appDAO.findById(Gender.class, genderId);
    }
    else if ("maritalStatus".equals(updatePropertyFieldType))  {
      Integer msId; try { msId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {msId = null;}
      newValue = appDAO.findById(MaritalStatus.class, msId);
    }
    else if ("object".equals(updatePropertyFieldType))  {
      newValue = updatePropertyValue;
    }
    else if ("race".equals(updatePropertyFieldType))  {
      Integer raceId; try { raceId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {raceId = null;}
      newValue = appDAO.findById(Race.class, raceId);
    }
    else if ("radioBtnGroup".equals(updatePropertyFieldType))  {
      String[] splitProperty = formSectionPropertyName.split("\\.");
      String propertyName = splitProperty[0]; 
      String propertyKey = splitProperty[1]; 
      newValue = setNewRadioBtnGroup(formSectionPropertyName, propertyName, propertyKey);
    }
    else if ("usState".equals(updatePropertyFieldType))  {
      Integer stateId; try { stateId = new Integer(updatePropertyValue); } catch (NumberFormatException nfe) {stateId = null;}
      newValue = appDAO.findById(USState.class, stateId);
    }

    return newValue;
  }
  
  public void initialize(AppService service) throws Exception {
    this.init(service);
  }
  
  public void processUpdateField(String field, Object newValue, AppService service) throws Exception {
  }
  
  protected void init(AppService service) throws Exception {} 
  
  public static String setNewRadioBtnGroup(String formSectionPropertyName, String propertyName, String propertyKey) {
    return null;
  }
  
	@Override
	public ICriteriaTransformer searchCriteria() {
		return null;
	}
}