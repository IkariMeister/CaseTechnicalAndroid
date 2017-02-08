package com.jcgarcia.casetechtest.network.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.module.ForApplication;
import com.jcgarcia.casetechtest.ui.view.BluetoothConnectionView;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 8/2/17.
 */

public class BluetoothConnBroadcastReceiver extends BroadcastReceiver {


    private final BluetoothHelper helper;

    private final Context appContext;
    private BluetoothConnectionView presenter;

    @Inject
    public BluetoothConnBroadcastReceiver(BluetoothHelper pHelper,
                                          @ForApplication Context pAppContext) {
        this.helper = pHelper;
        this.appContext = pAppContext;
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
        }
    }

    public void setPresenter(BluetoothConnectionView presenter) {
        this.presenter = presenter;
    }
}
