package com.jcgarcia.casetechtest.contract.executor;

/**
 * Created by jcgarcia on 5/2/17.
 */
public class ExecutorFactory {

  public static InteractorExecutor provideThreadExecutor() {
    return new ThreadExecutor();
  }

  public static MainThreadExecutor provideMainThreadExecutor() {
    return new MainThreadExecutorImp();
  }

}
