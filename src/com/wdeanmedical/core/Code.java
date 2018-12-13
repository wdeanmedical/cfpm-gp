package com.wdeanmedical.core;

public class Code {
  public final static String RETURN_CODE_ENTITY_EXISTS = "entity_exists";
  public static String toType(String message) {
    return message.toLowerCase().trim().replace(' ', '_');
  }
}
