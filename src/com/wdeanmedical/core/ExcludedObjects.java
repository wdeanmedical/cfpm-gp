package com.wdeanmedical.core;

import java.util.Map;
import java.util.TreeMap;


public class ExcludedObjects {

  public static String AppDAO_getPatientMessagesByClinician = "password, salt";
  
  public static Map<String, String[]> list = new TreeMap<String, String[]>();
  
    public static void excludeObjects(Object obj) {
    if (obj == null) {
      return;
    }
    String className = obj.getClass().getSimpleName();
    
    if ("Patient".equals(className)) {
    }
    else if ("User".equals(className)) {
    }
    else if ("Message".equals(className)) {
    }
  }

  public ExcludedObjects() {
    list.put("Credentials",   new String[] {"password","salt"});
 }
 
}
