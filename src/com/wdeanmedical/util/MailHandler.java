
package com.wdeanmedical.util;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Patient;

public class MailHandler {

  private static final Logger log = Logger.getLogger(MailHandler.class.getName());
  

  public static void sendMimeMessage(String to, String from, String text, String subject, boolean isHtmlText) throws Exception {
    final String finalTo = to; 
    final String finalFrom = from; 
    final String finalText = text; 
    final String finalSubject = subject; 
    final boolean finalIsHtmlText = isHtmlText; 
    new Thread(new Runnable() {
      @Override
      public void run() {
       try {
        sendMimeMessage(finalTo, null,finalFrom, finalText, finalSubject, null, new byte[0], finalIsHtmlText);
       } 
       catch (Exception e) {
         log.info("Unable to send email");
         e.printStackTrace();
       }
     }
    }).start();
  }


  public static void sendMimeMessage(String to,String cc, String from, String text, String subject, String attachmentName, 
                              byte[] attachment1, boolean isHtmlText) throws Exception {
    from = (from==null || from.equals("")) ? "info@wdeanmedical.com" : from;

    String[] toArray = to.split(";");
    String[] ccList = null;

    if(cc!=null && cc.toString().trim().length()>0) {
      ccList = cc.split(";"); 
    }  
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
        
    Properties props = System.getProperties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", Core.smtpport);
    props.put("mail.smtp.localhost", "127.0.0.1");
    sender.setJavaMailProperties(props);
       

    sender.setHost(Core.smtphost);
    sender.getSession().setDebug(true);
    sender.setUsername(Core.mailAuthenticationUser);
    sender.setPassword(Core.mailAuthenticationPassword);
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED);
    mimeMessageHelper.setFrom(Core.mailFrom);
    mimeMessageHelper.setTo(toArray);
    mimeMessageHelper.setSubject(subject);

    if(cc!=null && cc.toString().trim().length()>0) {
      mimeMessageHelper.setCc(ccList);
    }
    if (!StringUtils.isEmpty(text)) { 
        mimeMessageHelper.setText(text,isHtmlText);
    }
    if (attachmentName != null && !attachmentName.equals("undefined") && attachment1 != null && attachment1.length > 0) {
      Resource resource = new ByteArrayResource(attachment1);
      mimeMessageHelper.addAttachment(attachmentName, resource);
    }
    if (Core.sendMail.equals(true)) {
      try {
        sender.send(message);
      } 
      catch (Exception e) {
        log.info("ERROR: sending mail to: " + to + ", from: " + from + ", with subject: " + subject);
        log.info("EXCEPTION: " + e.getMessage());
      }
    }
    else {
      log.info("Trigger to send email has been set to: " + Core.sendMail);
    }
    return;
  }    



  public static void sendSystemEmail(String templateName, 
                                     String title, 
                                     Patient patient, 
                                     Client client, 
                                     HttpServletRequest request,
                                     String linkPath, 
                                     Map<String,String> customAttributes,
                                     String recipientEmail
                                     ) throws Exception {
    String server = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    String templatePath = Core.appBaseDir + "/WEB-INF/email_templates";
    StringTemplateGroup group = new StringTemplateGroup("underwebinf", templatePath, DefaultTemplateLexer.class);
    StringTemplate st = group.getInstanceOf(templateName);
    String senderEmail = Core.mailFrom;
    
    st.setAttribute("server", server);
    st.setAttribute("specialty", Core.specialty);
    st.setAttribute("practice", Core.practice);
    st.setAttribute("link", server + linkPath);
   
    if (patient != null) { 
      String patientFullName = patient.getGuardianFullName();
      if (patientFullName == null) {     
         patientFullName = patient.getFirstName() + " " + patient.getLastName();
      }
      st.setAttribute("patient", patientFullName);
      st.setAttribute("email", patient.getEmail());
      recipientEmail = patient.getEmail();
      st.setAttribute("phone", patient.getPrimaryPhone());
    }
    
    
    if (client != null) { 
      String userFullName = client.getFirstName() + " " + client.getLastName();
      st.setAttribute("user", userFullName);
      st.setAttribute("email", client.getEmail());
      recipientEmail = client.getEmail();
      st.setAttribute("phone", client.getPrimaryPhone());
    }
    
    st.setAttribute("practitioner", Core.practiceClientProperties.getProperty("app.practitioner"));
    st.setAttribute("business_address", Core.practiceClientProperties.getProperty("app.business_address"));
    st.setAttribute("business_info_email", Core.practiceClientProperties.getProperty("app.business_info_email"));
    st.setAttribute("business_name", Core.practiceClientProperties.getProperty("app.business_name"));
    st.setAttribute("business_phone", Core.practiceClientProperties.getProperty("app.business_phone"));
    st.setAttribute("business_support_email", Core.practiceClientProperties.getProperty("app.business_support_email"));
    st.setAttribute("business_url", Core.practiceClientProperties.getProperty("app.business_url"));
    st.setAttribute("main_image", Core.practiceClientProperties.getProperty("system_email.portal_invitation.main_image"));
    st.setAttribute("bottom_logo", Core.practiceClientProperties.getProperty("system_email.portal_invitation.bottom_logo"));
    st.setAttribute("top_logo", Core.practiceClientProperties.getProperty("system_email.app_cancelled.top_logo"));
    
    if (customAttributes != null) {
      for (Map.Entry<String, String> entry : customAttributes.entrySet()){
        st.setAttribute(entry.getKey(), entry.getValue());
      }
    }
    
    sendMimeMessage(recipientEmail, senderEmail, st.toString(), title, true);
  }
  

}
