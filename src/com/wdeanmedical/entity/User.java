package com.wdeanmedical.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User extends Client implements Serializable {

  private static final long serialVersionUID = 8014584895711544530L;
  public static String[] PHI_FIELDS = new String[] {};
  
  public static final String OFFICE = "office";
  public static final String CLINICAL = "clinical";
  public static final Integer ANY_CLINICIAN = 1;

  private Credential credential;
  private Integer credentialId;
  private Division division;
  private Integer divisionId;
  private Department department;
  private Integer departmentId;
  private String groupName;
  private String practiceName;
  private Role role;
  private Integer roleId;
  private String userType;

  public User() {
  }


  @Column(name = "credential_id")
  public Integer getCredentialId() { return credentialId; }
  public void setCredentialId(Integer credentialId) { this.credentialId = credentialId; }

  @Column(name = "division_id")
  public Integer getDivisionId() { return divisionId; }
  public void setDivisionId(Integer divisionId) { this.divisionId = divisionId; }

  @Column(name = "department_id")
  public Integer getDepartmentId() { return departmentId; }
  public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

  @Column(name = "role_id")
  public Integer getRoleId() { return roleId; }
  public void setRoleId(Integer roleId) { this.roleId = roleId; }

  @Column(name = "group_name")
  public String getGroupName() { return groupName; }
  public void setGroupName(String groupName) { this.groupName = groupName; }

  @Column(name = "practice_name")
  public String getPracticeName() { return practiceName; }
  public void setPracticeName(String practiceName) { this.practiceName = practiceName; }

  @Column(name = "user_type")
  public String getUserType() { return userType; }
  public void setUserType(String userType) { this.userType = userType; }

  @Transient
  public Credential getCredential() { return credential; }
  public void setCredential(Credential credential) { this.credential = credential; }

  @Transient
  public Division getDivision() { return division; }
  public void setDivision(Division division) { this.division = division; }

  @Transient
  public Department getDepartment() { return department; }
  public void setDepartment(Department department) { this.department = department; }

  @Transient
  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }

}
