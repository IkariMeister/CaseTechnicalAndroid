package com.jcgarcia.casetechtest;

import android.content.Context;

import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.executor.AbstractInteractor;
import com.jcgarcia.casetechtest.contract.executor.InteractorExecutor;
import com.jcgarcia.casetechtest.contract.executor.MainThreadExecutor;
import com.jcgarcia.casetechtest.contract.executor.UseCaseListener;
import com.jcgarcia.casetechtest.datasource.DataSource;
import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;
import com.jcgarcia.casetechtest.module.ForApplication;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class Register extends AbstractInteractor<Register.RegisterListener> {


    private final RadioDataSourceFactory mFactory;

    private boolean state;

    @Inject
    public Register(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor,
                    RadioDataSourceFactory pFactory) {
        super(interactorExecutor, mainThreadExecutor);
        this.mFactory = pFactory;
    }


    public void execute(boolean state, RegisterListener listener) {
        this.state = state;
        this.listener = listener;
        getInteractorExecutor().run(this);
    }

    @Override
    public void run() {
        doRegister(this.state);
    }

    public void doRegister(boolean on) {
        try {
            if (on) {
                mFactory.setType(RadioDataSourceFactory.RADIO_ON);
            } else {
                mFactory.setType(RadioDataSourceFactory.RADIO_OFF);
            }
            executionOk(true);
        } catch (Exception e) {
            executionError(e);
        }
    }

    public interface RegisterListener extends UseCaseListener<Boolean> {

    }


}
