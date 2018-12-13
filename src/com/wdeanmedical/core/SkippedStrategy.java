package com.wdeanmedical.core;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.wdeanmedical.annotations.GsonSkip;

public class SkippedStrategy implements ExclusionStrategy  {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes attr) {
		return attr.getAnnotation(GsonSkip.class) != null;
	}

}
