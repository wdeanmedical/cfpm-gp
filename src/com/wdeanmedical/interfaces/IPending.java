package com.wdeanmedical.interfaces;

public interface IPending {
  void create() throws Exception;
  void destroy() throws Exception;
  void setErrorMessage(String string) throws Exception;
}
