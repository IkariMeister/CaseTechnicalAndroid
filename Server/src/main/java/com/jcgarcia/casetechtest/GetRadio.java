package com.jcgarcia.casetechtest;

import android.content.Context;

import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.executor.AbstractInteractor;
import com.jcgarcia.casetechtest.contract.executor.InteractorExecutor;
import com.jcgarcia.casetechtest.contract.executor.MainThreadExecutor;
import com.jcgarcia.casetechtest.contract.executor.UseCaseListener;
import com.jcgarcia.casetechtest.datasource.DataSource;
import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;
import com.jcgarcia.casetechtest.module.ForApplication;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class GetRadio extends AbstractInteractor<GetRadio.GetRadioListener> {



    private final DataSource<RadioInfo> mDS;


    @Inject
    public GetRadio(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor,
                    RadioDataSourceFactory pFactory) {
        super(interactorExecutor, mainThreadExecutor);
        this.mDS = pFactory.getDataSource();
    }

    public void execute(GetRadioListener  listener) {
        this.listener = listener;
        getInteractorExecutor().run(this);
    }

    public void doGetRadio() {
        try {
            RadioInfo result = mDS.getCurrent();
            listener.onSuccess(result);

        } catch (Exception e) {
            listener.onError(e);
        }
    }

    @Override
    public void run() {
        doGetRadio();
    }

    public interface GetRadioListener extends UseCaseListener<RadioInfo> {

    }
}
