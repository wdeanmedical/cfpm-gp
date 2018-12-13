package com.wdeanmedical.entity.transaction.pending.stripe;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.wdeanmedical.entity.transaction.pending.Pending;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "stripe_pending_transaction")
public abstract class StripePendingTransaction extends Pending{
  private String type;

  public void setType(String type) {
    this.type = type;
  }

  @Column(name="type", insertable=false, updatable=false)
  public String getType() {return type;}
}
