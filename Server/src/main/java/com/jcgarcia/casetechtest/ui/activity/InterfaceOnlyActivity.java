package com.jcgarcia.casetechtest.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcgarcia.casetechtest.R;
import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.view.activity.CaseTestActivity;
import com.jcgarcia.casetechtest.module.Presenter;
import com.jcgarcia.casetechtest.ui.presenter.InterfaceOnlyPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jcgarcia on 9/2/17.
 */

public class InterfaceOnlyActivity extends CaseTestActivity {

    @Bind(R.id.log)
    TextView logTV;
    @Inject
    @Presenter
    InterfaceOnlyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_only);
        ButterKnife.bind(this);
        ((CaseTechTestApp) getApplication()).inject(this);
        presenter.setView(this);
        presenter.onInitialize();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @OnClick(R.id.buttonGetRadio)
    public void onClickGetRadio(View view){
        updateLog("GetRadio message received");
        presenter.startGetRadio();
    }

    @OnClick(R.id.buttonRegisterOff)
    public void onClickRegisterOff(View view){
        updateLog("Register Off message received");
        presenter.startRegister(false);
    }

    @OnClick(R.id.buttonRegisterOn)
    public void onClickRegisterOn(View view){
        updateLog("Register On message received");
        presenter.startRegister(true);
    }

    public void updateLog(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logTV.append(message);
                logTV.append("\n");
            }
        });
    }

}
