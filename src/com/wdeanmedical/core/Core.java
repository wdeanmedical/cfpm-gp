package com.wdeanmedical.core;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;


public class Core {

  public static String appHtml;
  public static String appBaseDir;
  public static String encryptionKey;
  public static String filesHome;  
  public static String headshotPlaceholder;  
  public static String imageMagickHome;
  public static Boolean initComplete = false;
  public static Log logger;
  public static String mailAuthenticationPassword;
  public static String mailAuthenticationUser;
  public static String mailFrom;
  public static String mailPassword;
  public static String mailServer;
  public static String mailUserName;
  public static String patientDirPath;
  public static String practice;
  public static Properties appProperties;
  public static Properties clientProperties;
  public static Properties practiceAppProperties;
  public static Properties practiceClientProperties;
  public static Boolean sendMail;
  public static ServletContext servletContext;
  public static Integer sessionTimeout;
  public static String smtphost;
  public static String smtpport;
  public static String specialty;
  public static Properties specialtyClientProperties;
  public static String stripeApiVersion;
  public static String stripeTestKey;
  public static String timeZone;
  public static String tmpDir;
  public static Boolean useImageMagick;
}
