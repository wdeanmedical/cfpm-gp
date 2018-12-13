package com.wdeanmedical.util;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wdeanmedical.interfaces.IMatcher;

public class Util {

  public static Method getMethod(Class<?> Klass, String name, Class<?>...parameterTypes) {
    Method method = null;
    try {
      method = Klass.getMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
     
    } catch (SecurityException e) {
    }
    return method;
  }
  public static String buildFullName(String firstName, String middleName, String lastName) {
    middleName = StringUtils.isNotEmpty(middleName) ? middleName + " " : ""; 
    String fullName = firstName + " " + middleName + lastName;
    return fullName;
  }
 
  public static void setProperties(Object object, String[] properties, Object from) throws Exception {
    Object value;
    for(String property: properties) {      
      value = PropertyUtils.getProperty(from, property);
      PropertyUtils.setProperty(object, property, value);
    }
  }
  
  public static void setProperties(Object object, String[] properties, Object[] from) throws Exception {
    Integer index=0;
    for(String property: properties) {      
      PropertyUtils.setProperty(object, property, from[index]);
      index+=1;
    }
  }
  
  public static Date datePlusOne(Date date) {
    Calendar cal = Calendar.getInstance();    
    cal.setTime(date);    
    cal.add( Calendar.DATE, 1 );    
    return cal.getTime();  
  }
  
  public static String capitalize(String s) {
    return Character.toUpperCase(s.charAt(0)) + s.substring(1); 
  }
  
  
  public static int getAge(Date dateOfBirth) {
    Calendar today = Calendar.getInstance();
    Calendar birthDate = Calendar.getInstance();
    int age = 0;

    birthDate.setTime(dateOfBirth);
    if (birthDate.after(today)) {
      throw new IllegalArgumentException("Can't be born in the future");
    }

    age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
    if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) || (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))) {
      age--;
     // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
    }
    else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) && (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))) {
      age--;
    }
    return age;
  }
 
  public static <T> T getOne(List<T> list, IMatcher matcher) {
    for(T t: list) {
       if(matcher.matches(t))  {
        return t;
      }
    }
    return null;
  }
  public static void logServletPath(Logger log, String module, String servletPath, String pathInfo) {
    String message = "service path: " + servletPath + pathInfo; 
    if (StringUtils.isNotEmpty(module)) { message += ", module: " + module; }
    log.info(message);
  }
}
