/*
 * WDean Medical is distributed under the
 * GNU Lesser General Public License (GNU LGPL).
 * For details see: http://www.wdeanmedical.com
 * copyright 2013-2014 WDean Medical
 */
 
package com.wdeanmedical.gp.entity.form;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wdeanmedical.gp.entity.DxCode;
import com.wdeanmedical.gp.entity.Lab;
import com.wdeanmedical.gp.entity.TxCode;
import com.wdeanmedical.service.AppService;

@Entity
@Table(name = "exam")
@Inheritance(strategy = InheritanceType.JOINED)
public class Exam extends ClosedEncounters implements Serializable {

  private static final long serialVersionUID = 1462745762564975233L;

  private static final String NAME = "exam";

  private String hs;
  private String heartRhythm;
  private String diagnosis;
  private String treatmentPlan;
  private String diagramPath;
  private List<DxCode> dxCodes;
  private List<TxCode> txCodes;
  private Lab lab;
  
  public Exam() {
    this.name = NAME;
  }
 
  @Column(name = "hs")
  public String getHs() { return hs; }
  public void setHs(String hs) { this.hs = hs; }

  @Column(name = "heart_rhythm")
  public String getHeartRhythm() { return heartRhythm; }
  public void setHeartRhythm(String heartRhythm) { this.heartRhythm = heartRhythm; }

  @Column(name = "diagnosis")
  public String getDiagnosis() { return diagnosis; }
  public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

  @Column(name = "treatment_plan")
  public String getTreatmentPlan() { return treatmentPlan; }
  public void setTreatmentPlan(String treatmentPlan) { this.treatmentPlan = treatmentPlan; }

  @Column(name = "diagram_path")
  public String getDiagramPath() { return diagramPath; }
  public void setDiagramPath(String diagramPath) { this.diagramPath = diagramPath; }

  @Transient
  public List<TxCode> getTxCodes() {
    return txCodes;
  }

  @Transient
  public void setTxCodes(List<TxCode> txCodes) {
    this.txCodes = txCodes;
  }

  @Transient
  public List<DxCode> getDxCodes() {
    return dxCodes;
  }

  public void setDxCodes(List<DxCode> dxCodes) {
    this.dxCodes = dxCodes;
  }

  @Transient
  public Lab getLab() {
    return lab;
  }

  public void setLab(Lab lab) {
    this.lab = lab;
  } 
  
  @Override
  protected void init(AppService service) throws Exception {
    Integer encounterId = getEncounterId();
    service.loadList(DxCode.class, "encounterId", encounterId, "dxCodes", this);
    service.loadList(TxCode.class, "encounterId", encounterId, "txCodes", this);
    service.loadAssociation(Lab.class, "encounterId", encounterId , "lab", this);
  }
}
