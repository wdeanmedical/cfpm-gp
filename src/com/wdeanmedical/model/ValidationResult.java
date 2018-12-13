package com.wdeanmedical.model;

import org.apache.commons.lang.StringUtils;

import com.wdeanmedical.core.Code;
import com.wdeanmedical.interfaces.IError;

public class ValidationResult implements IError {
  public Boolean isValid;
  private String errorMsg;
  private Integer errorCode;
  private String errorType;
  public ValidationResult valid() {
    this.isValid = true;
    return this;
  }
  public String getErrorMsg() {
    return errorMsg;
  }
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  public Integer getErrorCode() {
    return errorCode;
  }
  public ValidationResult setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }
  public ValidationResult invalid(String errorMsg, String errorType) {
    this.isValid = false;
    setErrorMsg(errorMsg);  
    setErrorType(errorType);
    return this;
  }
  public ValidationResult invalid(String errorMsg) {
    this.isValid = false;
    setErrorMsg(errorMsg);  
    if(StringUtils.isNotEmpty(errorMsg)) {
    setErrorType(Code.toType(errorMsg));
    }
    return this;
  }
  public String getErrorType() {
    return errorType;
  }
  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }
}
