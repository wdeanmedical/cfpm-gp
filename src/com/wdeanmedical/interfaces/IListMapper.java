package com.wdeanmedical.interfaces;

public interface IListMapper<R> {
  <T> R map(T item) throws Exception;
}
