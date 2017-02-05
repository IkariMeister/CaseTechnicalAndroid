package com.jcgarcia.casetechtest.contract.executor;

import android.os.Handler;
import android.os.Looper;
/**
 * Created by jcgarcia on 5/2/17.
 */
public class MainThreadExecutorImp implements MainThreadExecutor {

  private Handler handler;

  public MainThreadExecutorImp() {
    this.handler = new Handler(Looper.getMainLooper());
  }

  @Override
  public void execute(Runnable runnable) {
    handler.post(runnable);
  }
}