package com.jcgarcia.casetechtest.ui.presenter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.contract.view.presenter.PresenterImpl;
import com.jcgarcia.casetechtest.network.bluetooth.BluetoothConnBroadcastReceiver;
import com.jcgarcia.casetechtest.ui.activity.MainActivity;
import com.jcgarcia.casetechtest.ui.view.BluetoothConnectionView;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 8/2/17.
 */

public class MainPresenter extends PresenterImpl<MainActivity> implements BluetoothConnectionView {

    private final BluetoothConnBroadcastReceiver receiver;

    @Inject
    public MainPresenter(BluetoothConnBroadcastReceiver receiver) {
        super();
        this.receiver = receiver;
        receiver.setPresenter(this);
    }

    @Override
    public void onInitialize() {
        registerBroadcastReceiver();
    }

    @Override
    public void onStop() {
        unregisterBroadcastReceiver();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBluetoothConnect() {
        view.viewEstateConnected();
    }

    @Override
    public void onBluetoothDisconnect(String message) {
        view.viewEstateDisconnected();
    }

    @Override
    public void onBluetoothStateChanged(int idStringState) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Const.REQUEST_ENABLE_BLUETOOTH:
                if (resultCode == Activity.RESULT_OK) {
                    launchSelectDeviceActivity();
                }
                break;
        }
    }

    public void launchSelectDeviceActivity() {
        view.startActivityForResult(new Intent("android.bluetooth.devicepicker.action.LAUNCH").putExtra(
                "android.bluetooth.devicepicker.extra.NEED_AUTH", false)
                .putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 0)
                .setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS), Const.REQUEST_SELECT_BLUETOOTH);
    }

    public void startEnableBluetooth() {
        Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        view.startActivityForResult(enableBluetoothIntent, Const.REQUEST_ENABLE_BLUETOOTH);
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Const.ACTION_DEVICE_SELECTED);
        intentFilter.addAction(Const.ACTION_BLUETOOTH_ON_CONNECT);
        intentFilter.addAction(Const.ACTION_BLUETOOTH_ON_DISCONNECT);
        intentFilter.addAction(Const.ACTION_BLUETOOTH_ON_STATE_CHANGED);
        view.registerReceiver(receiver, intentFilter);
    }

    private void unregisterBroadcastReceiver() {
        view.unregisterReceiver(receiver);
    }


}
