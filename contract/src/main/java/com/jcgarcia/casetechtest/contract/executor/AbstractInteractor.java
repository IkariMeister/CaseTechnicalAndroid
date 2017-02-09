package com.jcgarcia.casetechtest.contract.executor;

/**
 * Created by jcgarcia on 9/2/17.
 */

public abstract class AbstractInteractor<T extends UseCaseListener> implements Interactor {
    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;

    protected UseCaseListener listener;

    public AbstractInteractor(InteractorExecutor interactorExecutor,
                              MainThreadExecutor mainThreadExecutor) {
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public InteractorExecutor getInteractorExecutor() {
        return interactorExecutor;
    }

    public void setInteractorExecutor(InteractorExecutor interactorExecutor) {
        this.interactorExecutor = interactorExecutor;
    }

    public MainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public void setMainThreadExecutor(MainThreadExecutor mainThreadExecutor) {
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public void setListener(UseCaseListener listener) {
        this.listener = listener;
    }

    public void executionOk(final Object o){
        getMainThreadExecutor().execute(new Runnable() {
            @Override public void run() {
                listener.onSuccess(o);
            }
        });

    }

    public void executionError(final Object o){
        getMainThreadExecutor().execute(new Runnable() {
            @Override public void run() {
                listener.onError(o);
            }
        });

    }
}
