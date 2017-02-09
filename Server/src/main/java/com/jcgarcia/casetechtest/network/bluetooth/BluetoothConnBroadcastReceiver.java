package com.jcgarcia.casetechtest.network.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.module.ForApplication;
import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.ui.view.BluetoothConnectionView;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 8/2/17.
 */

public class BluetoothConnBroadcastReceiver extends BroadcastReceiver {


    private final BluetoothHelper helper;

    private final Context appContext;
    private final ClientConnection<BluetoothDevice> service;
    private BluetoothConnectionView presenter;

    @Inject
    public BluetoothConnBroadcastReceiver(BluetoothHelper pHelper,
                                          @ForApplication Context pAppContext,
                                          ClientConnection<BluetoothDevice> pService) {
        this.helper = pHelper;
        this.appContext = pAppContext;
        this.service = pService;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {
            case Const.ACTION_DEVICE_SELECTED:
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                helper.onDeviceSelected(appContext, bluetoothDevice);
                break;
            case Const.ACTION_BLUETOOTH_ON_CONNECT:
                presenter.onBluetoothConnect();
                break;
            case Const.ACTION_BLUETOOTH_ON_DISCONNECT:
                String mensaje = intent.getExtras().getString(Const.EXTRA_BLUETOOTH_DISCONNECT_MESSAGE);
                presenter.onBluetoothDisconnect(mensaje);
                break;
            case Const.ACTION_BLUETOOTH_ON_STATE_CHANGED:
                int idMensaje = intent.getExtras().getInt(Const.EXTRA_BLUETOOTH_STATE_CHANGED_ID_STRING);
                presenter.onBluetoothStateChanged(idMensaje);
                break;
            case Const.ACTION_BLUETOOTH_CONNECT:
                bluetoothDevice =
                        intent.getExtras().getParcelable(Const.EXTRA_BLUETOOTH_DEVICE_SELECTED);
                service.onConnected(bluetoothDevice);
                break;

            case Const.ACTION_BLUETOOTH_DISCONNECT:
                service.onStop();
                break;
        }
    }

    public void setPresenter(BluetoothConnectionView presenter) {
        this.presenter = presenter;
    }
}
