package com.jcgarcia.casetechtest.contract.executor;

/**
 * Created by jcgarcia on 5/2/17.
 */
public interface MainThreadExecutor {

  void execute(Runnable runnable);
}