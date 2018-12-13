package com.wdeanmedical.core;

import java.util.Map;
import java.util.TreeMap;

import com.wdeanmedical.entity.BaseEntity;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.User;


public class ExcludedFields {
  public static String CREDENTIALS = "password, salt";
   
  public static Map<String, String[]> list = new TreeMap<String, String[]>();

  public ExcludedFields() {
    list.put("Credentials",   new String[] {"password","salt"});
    list.put("BaseEntity",    new String[] {"lastAccessed","lastUpdated", "createdDate"});
  }
 
 
   public static void excludeFields(Object obj) {
    if (obj == null) {
      return;
    }
    String className = obj.getClass().getSimpleName();
    
    ((BaseEntity)obj).setLastAccessed(null);
    ((BaseEntity)obj).setLastUpdated(null);
    ((BaseEntity)obj).setCreatedDate(null);
    
    if ("Patient".equals(className)) {
      Patient patient = (Patient)obj;
      patient.setPassword(null);
      patient.setSalt(null);
    }
    else if ("User".equals(className)) {
      User user = (User)obj;
      user.setPassword(null);
      user.setSalt(null);
    }
    
  }
}
