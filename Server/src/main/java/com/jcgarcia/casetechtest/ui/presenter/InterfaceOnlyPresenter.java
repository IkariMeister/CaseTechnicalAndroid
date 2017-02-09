package com.jcgarcia.casetechtest.ui.presenter;

import android.content.Context;

import com.jcgarcia.casetechtest.GetRadio;
import com.jcgarcia.casetechtest.Register;
import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.contract.view.presenter.PresenterImpl;
import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;
import com.jcgarcia.casetechtest.mapper.JsonRadioInfoMapper;
import com.jcgarcia.casetechtest.module.ForApplication;
import com.jcgarcia.casetechtest.ui.activity.InterfaceOnlyActivity;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 9/2/17.
 */

public class InterfaceOnlyPresenter extends PresenterImpl<InterfaceOnlyActivity>
        implements GetRadio.GetRadioListener {

    private Mapper<RadioInfo,String> jsonMapper;


    private Register.RegisterListener registerListener = new Register.RegisterListener() {
        @Override
        public void onSuccess(Boolean value) {
            view.updateLog("{register: \"ok\"}");

        }

        @Override
        public void onError(Object e) {
            view.updateLog("{register: \"ko\"}");

        }
    };

    @Inject
    public InterfaceOnlyPresenter(@ForApplication Context context,
                                  JsonRadioInfoMapper jsonMapper){
        super();
        this.jsonMapper = jsonMapper;
        RadioDataSourceFactory.getInstance().setContext((CaseTechTestApp)context);

    }
    @Override
    public void onInitialize() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    public void startGetRadio() {
        GetRadio usecase = new GetRadio(getInteractorExecutor(),getMainThreadExecutor(),
                RadioDataSourceFactory.getInstance());
        usecase.execute(this);
    }

    public void startRegister(boolean value) {
        Register usecase = new Register(getInteractorExecutor(),getMainThreadExecutor(),
                RadioDataSourceFactory.getInstance());
        usecase.execute(value,registerListener);
    }

    @Override
    public void onSuccess(RadioInfo value) {
        view.updateLog(jsonMapper.map(value));
    }

    @Override
    public void onError(Object e) {
        if(e instanceof Exception){
            Exception ex = (Exception)e;
            view.updateLog(ex.getMessage());
        }
    }


}
