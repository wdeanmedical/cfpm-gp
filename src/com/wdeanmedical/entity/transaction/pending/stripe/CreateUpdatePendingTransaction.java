package com.wdeanmedical.entity.transaction.pending.stripe;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CreateUpdate")
public class CreateUpdatePendingTransaction extends StripePendingTransaction implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
