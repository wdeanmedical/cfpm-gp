package com.wdeanmedical.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wdeanmedical.core.SkippedStrategy;

public final class JSONUtils {
  private static final Gson gson = new GsonBuilder()
      .setExclusionStrategies(new SkippedStrategy())
      .create();

  private JSONUtils() {
  }
  
  public static Gson getGson() {
		return gson;
  }

  public static <T> T clone(Object o, Class<T> T) {
    return gson.fromJson(gson.toJson(o), T);
  }
  
  public static String toJson(Object o) {
    return gson.toJson(o);
  }
  
  public static boolean isJSONValid(String JSON_STRING, Class<?> clazz) {
    try {
      gson.fromJson(JSON_STRING, clazz);
      return true;
    } catch (com.google.gson.JsonSyntaxException jse) {
      return false;
    } catch (com.google.gson.JsonParseException jpe) {
      return false;
    }
  }
}