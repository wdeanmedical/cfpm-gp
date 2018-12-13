package com.wdeanmedical.entity.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.entity.BaseEntity;

@Entity
@Table(name = "practice_form")
public class PracticeForm extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -7135393589707880277L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String GENERAL = "general";
  public static final String PATIENT_INTAKE = "patient_intake";
  public static final String BUILT_IN = "built_in";

  String formType; 
  String loader;
  String listloader;
  String closer;
  String reopener;
  String printLoader;
  String printRenderer;
  String name;
  Integer sortOrder; 
  String template;
  String printTemplate;
  String className;
  String title;
  Object data;
  Boolean single;
  String validator;
  
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @Column(name = "form_type")
  public String getFormType() { return formType; }
  public void setFormType(String formType) { this.formType = formType; }
  
  @Column(name = "sort_order")
  public Integer getSortOrder() { return sortOrder; }
  public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
  
  @Column(name = "loader")
  public String getLoader() { return loader; }
  public void setLoader(String loader) { this.loader = loader; }
  
  @Column(name = "reopener")
  public String getReopener() { return reopener; }
  public void setReopener(String reopener) { this.reopener = reopener; }
  
  @Column(name = "closer")
  public String getCloser() { return closer; }
  public void setCloser(String closer) { this.closer = closer; }
  
  @Column(name = "print_loader")
  public String getPrintLoader() { return printLoader; }
  public void setPrintLoader(String printLoader) { this.printLoader = printLoader; }
  
  @Column(name = "print_renderer")
  public String getPrintRenderer() { return printRenderer; }
  public void setPrintRenderer(String printRenderer) { this.printRenderer = printRenderer; }
  
  @Column(name = "template")
  public String getTemplate() { return template; }
  public void setTemplate(String template) { this.template = template; }
  
  @Column(name = "print_template")
  public String getPrintTemplate() { return printTemplate; }
  public void setPrintTemplate(String printTemplate) { this.printTemplate = printTemplate; }
  
  @Column(name = "class_name")
  public String getClassName() { return className; }
  public void setClassName(String className) { this.className = className; }
  
  @Column(name = "is_single")
  public Boolean getSingle() { return single; }
  public void setSingle(Boolean single) { this.single = single; }
  
  @Column(name = "title")
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  @Column(name = "listloader")
  public String getListloader() { return listloader; }
  public void setListloader(String listloader) { this.listloader = listloader; }
  
  @Transient
  public Object getData() { return data; }
  public void setData(Object data) { this.data = data; }
  
  @Column(name ="validator")
  public String getValidator() {
    return validator;
  }
  public void setValidator(String validator) {
    this.validator = validator;
  }
}
