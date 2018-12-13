package com.wdeanmedical.interfaces;

import com.wdeanmedical.entity.BaseEntity;

public interface IPassword {

  void setPassword(String encodedPassword);

  void setSalt(String newSalt);

  BaseEntity getBaseEntity();

  void setPasswordCreated(Boolean b);

  String getName();

}
