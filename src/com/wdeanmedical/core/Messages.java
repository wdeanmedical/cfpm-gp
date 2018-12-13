package com.wdeanmedical.core;

public class Messages {
  public static String exists(Class<?> Klass ) {
    return Klass.getSimpleName() + " already exists";
  }
  public class Encounter {
    public final static String LAST_ENCOUNTER_NOT_COMPLETED = "Last Encounter Not Completed";
  }
  public class OT {
  		public final static String EVALUATIONS_REQUIRED = "An Evaluation is required to proceed";
  }
}
