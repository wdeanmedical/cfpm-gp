package com.wdeanmedical.interfaces;

import org.hibernate.Criteria;

public interface ICriteriaTransformer {
    void transform(Criteria criteria);
}
