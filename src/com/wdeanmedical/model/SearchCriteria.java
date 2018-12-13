package com.wdeanmedical.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.wdeanmedical.interfaces.ICriteriaTransformer;

public class SearchCriteria implements ICriteriaTransformer {
	private List<Criterion> list;
	
	public SearchCriteria(List<Criterion> list) {
		setList(list);
	}

	public List<Criterion> getList() {
		return list;
	}

	public void setList(List<Criterion> list) {
		this.list = list;
	}

	@Override
	public void transform(Criteria criteria) {
		for(Criterion criterion: list) {
			criteria.add(Restrictions.eq(criterion.name, criterion.value));
		}
	}
}
