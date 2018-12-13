package com.wdeanmedical.service;


import java.net.MalformedURLException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.dto.AdminDTO;
import com.wdeanmedical.entity.ActivityLog;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.persistence.AdminDAO;
import com.wdeanmedical.util.OneWayPasswordEncoder;

public class AdminService extends AppService {

  private static Log log = LogFactory.getLog(AppService.class);
  public static int RETURN_CODE_DUP_USERNAME = -1;
  public static int RETURN_CODE_DUP_USER_EMAIL = -3;
  public static int RETURN_CODE_INVALID_PASSWORD = -2;
  
  private AdminDAO adminDAO;


  public AdminService() throws MalformedURLException {
    super();
    adminDAO = (AdminDAO) wac.getBean("adminDAO");
  }
      
      
      
  public void activateUser(AdminDTO dto) throws Exception {
    User user = adminDAO.findById(User.class, dto.id);
    user.setStatus(Client.ACTIVE);
    adminDAO.update(user);
    logEvent(dto, ActivityLog.ACTIVATE_USER, "AdminService activateUser()", null, null);
  }
  
  
  
  public AdminDTO checkUsername(AdminDTO adminDTO) throws Exception {
    adminDTO.result = adminDAO.checkUsername(adminDTO.username);
    return adminDTO;
  }
  
  
  
  public AdminDTO checkUserUsername(AdminDTO adminDTO) throws Exception {
    adminDTO.result = adminDAO.checkUsername(adminDTO.username);
    return adminDTO;
  }
  

  
  public void deactivateUser(AdminDTO dto) throws Exception {
    User user = adminDAO.findById(User.class, dto.id);
    user.setStatus(Client.INACTIVE);
    adminDAO.update(user);
    logEvent(dto, ActivityLog.DEACTIVATE_USER, "AdminService deactivateUser()", null, null);
  }
  
  
  
  public void getUsers(AdminDTO dto) throws Exception {
    dto.list = adminDAO.findUsers();
    logEvent(dto, ActivityLog.VIEW_USERS, "AdminService getUsers()", null, null);
  }
  
  
  
  public void deleteUser(AdminDTO dto) throws Exception {
    User user = adminDAO.findById(User.class, dto.id);
    user.setStatus(Client.DELETED);
    adminDAO.update(user);
    logEvent(dto, ActivityLog.DELETE_USER, "AdminService deleteUser()", null, null);
  }


  
  public  void saveNewUser(AdminDTO dto) throws Exception {
  
   if(adminDAO.checkUsername(dto.username) == false) {
      dto.result = false;
      dto.errorMsg = "Username already in system";
      dto.returnCode = RETURN_CODE_DUP_USERNAME;
      return;
    }
    if(adminDAO.checkEmail(dto.email) == false) {
      dto.result = false;
      dto.errorMsg = "Email already in system";
      dto.returnCode = RETURN_CODE_DUP_USER_EMAIL;
      return;
    }
    if (testPassword(dto.password) == false) {
      dto.result = false;
      dto.errorMsg = "Insufficient Password";
      dto.returnCode = RETURN_CODE_INVALID_PASSWORD;
      return;
    }
    
    User user = new User();
    user.setClientType(Client.USER);
    String salt = UUID.randomUUID().toString();
    user.setSalt(salt);
    String encodedPassword = OneWayPasswordEncoder.getInstance().encode(dto.password, salt);
    user.setPassword(encodedPassword);
    
    user.setFirstName(dto.firstName);
    user.setMiddleName(dto.middleName);
    user.setLastName(dto.lastName);
    user.setUsername(dto.username);
    user.setPrimaryPhone(dto.primaryPhone);
    user.setSecondaryPhone(dto.secondaryPhone);
    user.setEmail(dto.email);
    user.setUserType(dto.userType);
    user.setPager(dto.pager);
    user.setRoleId(dto.roleId);
    user.setCredentialId(dto.credentialId);
    user.setDepartmentId(dto.departmentId);
    user.setDivisionId(dto.divisionId);
    user.setStatus(Client.ACTIVE);
    user.setPasswordCreated(true);
    adminDAO.createUser(user);
    logEvent(dto, ActivityLog.CREATE_USER, "AdminService saveNewUser()", null, null);
  }
  
  
  
  @Override
  public boolean testPassword(String password) {
  
   if (password.length() < 6) {
    log.info("Submitted passwords is not at least six characters long");
    return false;
    }
    Pattern lowerCasePattern = Pattern.compile("[a-z]+");
    Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        
    Pattern upperCasePattern = Pattern.compile("[A-Z]+");
    Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        
    if (lowerCaseMatcher.find() == false || upperCaseMatcher.find() == false) {
      log.info("Sumitted passwords does not include at least one uppercase and one lowercase letter");
      return false;
    }
          
    Pattern numericPattern = Pattern.compile("\\d+");
    Matcher numericMatcher = numericPattern.matcher(password);
        
    Pattern punctuationPattern = Pattern.compile("\\p{Punct}+");
    Matcher punctuationMatcher = punctuationPattern.matcher(password);
         
    if (numericMatcher.find() == false || punctuationMatcher.find() == false) {
      log.info("Submitted passwords does not include at least one numeric character and one punctuation character");
      return false;
    }
    return true;
  }

  

  public void updateUser(AdminDTO dto) throws Exception {
    User user = adminDAO.findById(User.class, dto.id);
    String property = dto.updateProperty;
    String value = dto.updatePropertyValue;
    if (property.equals("firstName")) {user.setFirstName(value);} 
    else if (property.equals("middleName")) {user.setMiddleName(value);} 
    else if (property.equals("lastName")) {user.setLastName(value);} 
    else if (property.equals("pager")) {user.setPager(value);} 
    else if (property.equals("primaryPhone")) {user.setPrimaryPhone(value);} 
    else if (property.equals("secondaryPhone")) {user.setSecondaryPhone(value);} 
    else if (property.equals("credentialId")) { user.setCredentialId(new Integer(value));} 
    else if (property.equals("roleId")) { user.setRoleId(new Integer(value));} 
    else if (property.equals("departmentId")) { user.setDepartmentId(new Integer(value));} 
    else if (property.equals("divisionId")) { user.setDivisionId(new Integer(value));} 
    else if (property.equals("userType")) {user.setUserType(value);} 
    
    else if (property.equals("username") && !value.equals(user.getUsername())) {
     if(adminDAO.checkUsername(value) == false) {
        dto.result = false;
        dto.errorMsg = "Username already in system";
        dto.returnCode = RETURN_CODE_DUP_USERNAME;
        return;
      }
      user.setUsername(value);
    } 
    else if (property.equals("email") && !value.equals(user.getEmail())) {
     if(adminDAO.checkEmail(value) == false) {
        dto.result = false;
        dto.errorMsg = "Email already in system";
        dto.returnCode = RETURN_CODE_DUP_USER_EMAIL;
        return;
      }
      user.setEmail(value);
    }
    else if (property.equals("password")) {
      if (testPassword(value) == false) {
        dto.result = false;
        dto.errorMsg = "Insufficient Password";
        dto.returnCode = RETURN_CODE_INVALID_PASSWORD;
        return;
      }
      String salt = UUID.randomUUID().toString();
      user.setSalt(salt);
      String encodedPassword = OneWayPasswordEncoder.getInstance().encode(value, salt);
      user.setPassword(encodedPassword);
    } 
    
    adminDAO.update(user);
    logEvent(dto, ActivityLog.UPDATE_USER, "AdminService updateUser()", property, null);
  }

}
