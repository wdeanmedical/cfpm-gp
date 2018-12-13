package com.wdeanmedical.interfaces;

public interface IItemTransformer {
  <T> void transform(T item) throws Exception;
}
