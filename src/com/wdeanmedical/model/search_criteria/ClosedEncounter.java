package com.wdeanmedical.model.search_criteria;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.wdeanmedical.interfaces.ICriteriaTransformer;

public class ClosedEncounter implements ICriteriaTransformer {
	
	@Override
	public void transform(Criteria criteria) {
		Criteria encounter = criteria.createCriteria("encounter");
		encounter.add(Restrictions.eq("closed", true));
	}
}
