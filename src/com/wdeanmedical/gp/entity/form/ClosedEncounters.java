package com.wdeanmedical.gp.entity.form;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.wdeanmedical.annotations.GsonSkip;
import com.wdeanmedical.entity.form.WDMForm;
import com.wdeanmedical.interfaces.ICriteriaTransformer;
import com.wdeanmedical.model.search_criteria.ClosedEncounter;

@MappedSuperclass()
public abstract class ClosedEncounters extends WDMForm {

  @GsonSkip
  private Encounter encounter;

  @OneToOne
  @JoinColumn(name = "encounter")
  public Encounter getEncounter() {
	return encounter;
  }

  public void setEncounter(Encounter encounter) {
	this.encounter = encounter;
  }

  @Override
  public ICriteriaTransformer searchCriteria() {
	return new ClosedEncounter();
  }
  
  @Transient
  public Integer getEncounterId() {
     return encounter.getId();
  }
}
