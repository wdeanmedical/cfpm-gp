package com.wdeanmedical.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wdeanmedical.interfaces.ICanExecute;
import com.wdeanmedical.interfaces.IPending;

public class BackgroundService {
  private final ExecutorService pool;
  private static BackgroundService instance;
  ICanExecute iCanExecute;
  private BackgroundService(ICanExecute iCanExecute) {
    pool = Executors.newCachedThreadPool();
    this.iCanExecute=iCanExecute;
  }
  public static BackgroundService getInstance(ICanExecute iCanExecute) {
    if (instance == null) {
      instance = new BackgroundService(iCanExecute);
    }
    return instance;
  }
  public void execute(final Callable<Void> callable, final IPending pending) throws Exception {
    if(iCanExecute.can()) {
      pool.execute(new Runnable() {
        @Override
        public void run(){
          try {
            pending.create(); 	
            callable.call();
            pending.destroy();
          } catch (Exception e) {
            try {
              pending.setErrorMessage(e.getMessage());
            } catch (Exception e1) {
              e1.printStackTrace();
            }
          } 
        }
      });
    }
  }
  public static void shutDown() {
    if (instance != null) {
      instance.pool.shutdown();
      instance = null;
    }
  }
}
