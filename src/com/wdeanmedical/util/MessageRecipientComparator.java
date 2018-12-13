package com.wdeanmedical.util;

import java.util.Comparator;

import com.wdeanmedical.model.MessageRecipient;


public class MessageRecipientComparator implements Comparator<MessageRecipient> {

    @Override
    public int compare(MessageRecipient o1, MessageRecipient o2) {
        int value1 = o1.lastName.compareTo(o2.lastName);
        if (value1 == 0) {
            int value2 = o1.firstName.compareTo(o2.firstName);
            return value2;
        }
        return value1;
    }
}