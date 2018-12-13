package com.wdeanmedical.service;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.SiteDTO;
import com.wdeanmedical.persistence.SiteDAO;
import com.wdeanmedical.util.MailHandler;

public class SiteService extends AppService {

  private static Log log = LogFactory.getLog(SiteService.class);
  
  private SiteDAO siteDAO;



  public SiteService() throws MalformedURLException {
    super();
    siteDAO = (SiteDAO) wac.getBean("siteDAO");
  }
  
  
  
  public void getClientProperties(SiteDTO dto) throws Exception {
    Core.practiceClientProperties.setProperty("practice", Core.practice);
    Core.practiceClientProperties.setProperty("specialty", Core.specialty);
    if (new Boolean(Core.practiceAppProperties.getProperty("app.multipractice")) == true) {
      try { 
        InputStream inputStream = new FileInputStream(Core.appBaseDir + "/WEB-INF/properties/practice/"+Core.practice+"/"+Core.practice+"_"+dto.siteKey+"_client.properties");
        Core.practiceClientProperties.load(inputStream);
      } 
      catch (IOException e) { log.info("Loading failed to load "+Core.practice+"_"+dto.siteKey+"_client.properties " + e.getMessage()); }
    }
    dto.practiceClientProperties = Core.practiceClientProperties;
  }
      
      
      
  public  boolean sendContactMessage(HttpServletRequest request, SiteDTO dto) throws Exception {
    String name = dto.name;
    String subject = "Contact Message From " + name;
    String email = dto.email;
    String phone = dto.phone;
    String title = subject;
    
    String message = "Name: "+name+"\n";
    message += "Phone: "+phone+"\n";
    message += "Email: "+email+"\n";
    message += "\n\n";
    message += dto.message;
    
    Map<String,String> customAttributes = new HashMap<String,String>();
    customAttributes.put("name", name);
    customAttributes.put("email", email);
    customAttributes.put("phone", phone);
    customAttributes.put("message", message);
    
    MailHandler.sendSystemEmail("contact_response", title, null, null, request, "", customAttributes, null);
    MailHandler.sendSystemEmail("contact_message", title, null, null, request, "", customAttributes, null);
    
    return true;
  }

}
