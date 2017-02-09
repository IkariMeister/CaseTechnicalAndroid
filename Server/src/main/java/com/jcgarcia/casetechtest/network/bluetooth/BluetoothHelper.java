package com.jcgarcia.casetechtest.network.bluetooth;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jcgarcia.casetechtest.contract.common.Const;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 8/2/17.
 */

public class BluetoothHelper {


    @Inject
    public BluetoothHelper() {

    }


    public boolean isBluetoothAdapterAvailable() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public boolean isBluetoothAdapterEnabled() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public void onDeviceSelected(Context context, BluetoothDevice device) {
        sendConnectBroadcast(context, device);
//        SessionManager sesionManager = new SessionManagerShared(context);
//        sesionManager.setBluetoothDeviceId(device.getAddress());
    }

    public void sendConnectBroadcast(Context context, BluetoothDevice bluetoothDevice) {
        Intent intent = new Intent(Const.ACTION_BLUETOOTH_CONNECT);

        Bundle extras = new Bundle();
        extras.putParcelable(Const.EXTRA_BLUETOOTH_DEVICE_SELECTED, bluetoothDevice);
        intent.putExtras(extras);
        context.sendBroadcast(intent);
    }

    public void sendDisconnectBroadcast(Context context) {
//        Intent intent = BluetoothCommunicationService.buildBluetoothDisconnectIntent();
//        context.sendBroadcast(intent);
    }

    @NonNull
    public Intent getBluetoothServiceIntent(Context context) {
        return new Intent(context, BluetoothCommunicationService.class);
    }

    public void startBluetoothService(Context context) {
        if (!isBluetoothServiceRunning(context)) {
            context.startService(getBluetoothServiceIntent(context));
        }
    }

    public void stopBluetoothService(Context context) {
        context.stopService(getBluetoothServiceIntent(context));
    }

    public boolean isBluetoothServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(
                Integer.MAX_VALUE)) {
            if ("com.articatelemedicina.estres.service.BluetoothCommunicationService".equals(
                    service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
