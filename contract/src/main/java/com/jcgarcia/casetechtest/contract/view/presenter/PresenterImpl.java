package com.jcgarcia.casetechtest.contract.view.presenter;

import com.jcgarcia.casetechtest.contract.executor.ExecutorFactory;
import com.jcgarcia.casetechtest.contract.executor.InteractorExecutor;
import com.jcgarcia.casetechtest.contract.executor.MainThreadExecutor;

/**
 * Created by jcgarcia on 5/2/17.
 */

public abstract class PresenterImpl<T extends PresenterImpl.BaseView> implements Presenter {

    protected MainThreadExecutor mainThreadExecutor;
    protected InteractorExecutor interactorExecutor;

    protected T view;

    public PresenterImpl() {
        initThreads();
    }

    public PresenterImpl(T view) {
        this.view = view;
        initThreads();
    }

    private void initThreads() {
        this.mainThreadExecutor = ExecutorFactory.provideMainThreadExecutor();
        this.interactorExecutor = ExecutorFactory.provideThreadExecutor();
    }

    protected T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    protected MainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    protected void setMainThreadExecutor(MainThreadExecutor mainThreadExecutor) {
        this.mainThreadExecutor = mainThreadExecutor;
    }

    protected InteractorExecutor getInteractorExecutor() {
        return interactorExecutor;
    }

    protected void setInteractorExecutor(InteractorExecutor interactorExecutor) {
        this.interactorExecutor = interactorExecutor;
    }


    public interface BaseView {

        void showDefaultError();

        void showEmptyCase();

        void showDefaultLoading();

        void hideDefaultLoading();

        boolean isTabletMode();
    }

}