/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "icd_9")
public class ICD9 extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -2021517125953417861L;
  
  private Integer codeType; 
  private String modifier;
  private String code;
  private String codeText;

  public ICD9() {
  }

  @Column(name = "code_type")
  public Integer getCodeType() { return codeType; }
  public void setCodeType(Integer codeType) { this.codeType = codeType; }

  @Column(name = "modifier")
  public String getModifier() { return modifier; }
  public void setModifier(String modifier) { this.modifier = modifier; }

  @Column(name = "code_text")
  public String getCodeText() { return codeText; }
  public void setCodeText(String codeText) { this.codeText = codeText; }

  @Column(name = "code")
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }



}
