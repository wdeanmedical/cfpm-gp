package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient_file")
public class PatientFile extends BaseEntity implements Serializable {
  
  private static final long serialVersionUID = -2329078627044034978L;
  public static String[] PHI_FIELDS = new String[] {};
  private String description;
  private String path;
  private String name;
  private Boolean deleted = false;
  private String type;
  private Integer patientId;
  private Integer clientId;
  private String clientType;
  
  @Column(name = "deleted", nullable=false, columnDefinition = "BOOLEAN DEFAULT false")
  public Boolean getDeleted() { return deleted; }
  public void setDeleted(Boolean deleted) { this.deleted = deleted; }
  
  @Column(name = "name")
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  @Column(name = "patient_id")
  public Integer getPatientId() { return patientId; }
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  
  @Column(name = "client_id")
  public Integer getClientId() { return clientId; }
  public void setClientId(Integer clientId) { this.clientId = clientId; }
  
  @Column(name = "client_type")
  public String getClientType() { return clientType; }
  public void setClientType(String clientType) { this.clientType = clientType; }
  
  @Column(name = "description")
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  @Column(name = "path")
  public String getPath() { return path; }
  public void setPath(String path) { this.path = path; } 
  
  @Column(name = "type")
  public String getType() { return type; }
  public void setType(String type) { this.type = type; }  
  
}
