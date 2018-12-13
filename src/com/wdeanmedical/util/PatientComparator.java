package com.wdeanmedical.util;

import java.util.Comparator;

import com.wdeanmedical.entity.Patient;


public class PatientComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        int value1 = o1.getLastName().compareTo(o2.getLastName());
        if (value1 == 0) {
            int value2 = o1.getFirstName().compareTo(o2.getFirstName());
            return value2;
        }
        return value1;
    }
}