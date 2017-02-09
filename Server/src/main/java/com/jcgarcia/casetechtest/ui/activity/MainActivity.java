package com.jcgarcia.casetechtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcgarcia.casetechtest.R;
import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.view.activity.CaseTestActivity;
import com.jcgarcia.casetechtest.module.Presenter;
import com.jcgarcia.casetechtest.network.bluetooth.BluetoothHelper;
import com.jcgarcia.casetechtest.ui.presenter.MainPresenter;
import com.jcgarcia.casetechtest.ui.view.BluetoothConnectionView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends CaseTestActivity implements BluetoothConnectionView {

    @Bind(R.id.textViewState)
    TextView stateTV;
    @Bind(R.id.buttonServer)
    View serverBtn;
    @Bind(R.id.log)
    TextView logTV;
    @Inject
    @Presenter
    MainPresenter presenter;
    @Inject
    BluetoothHelper bluetoothHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((CaseTechTestApp) getApplication()).inject(this);
        presenter.setView(this);
        presenter.onInitialize();


    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBluetoothConnect() {
    }

    @Override
    public void onBluetoothDisconnect(String message) {

    }

    @Override
    public void onBluetoothStateChanged(int idStringState) {

    }

    @OnClick(R.id.buttonServer)
    public void onClickServer(View view) {
        if (!bluetoothHelper.isBluetoothAdapterAvailable()) {
            showMessage(R.string.bluetooth_no_device);
            return;
        }

        if (!bluetoothHelper.isBluetoothAdapterEnabled()) {
            presenter.startEnableBluetooth();
            return;
        }

        presenter.launchSelectDeviceActivity();
    }


    public void viewEstateDisconnected() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stateTV.setText(R.string.bluetooth_state_disconnected);
//        viewHolder.botonConectar.setVisibility(View.VISIBLE);
//        viewHolder.botonDesconectar.setVisibility(View.GONE);
            }
        });
    }

    public void viewEstateConnected() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stateTV.setText(R.string.bluetooth_state_connected);
//        viewHolder.botonConectar.setVisibility(View.GONE);
//        viewHolder.botonDesconectar.setVisibility(View.VISIBLE);
            }
        });
    }
}
