package com.wdeanmedical.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TimeUtil {
	public static Date fromNow(Duration duration) {
		return Date.from(Instant.now().plus(duration));
	}

	public static boolean isBeforeNow(Date expiresAt) {
		return Date.from(Instant.now()).after(expiresAt);
	}
}
