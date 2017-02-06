package com.jcgarcia.casetechtest.client.usecase;

import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/**
 * Created by jcgacia on 6/2/17.
 */

public class FakeConnectionListener implements ConnectionListener {
    @Override
    public void onConnected(BluetoothSocket socket) {

    }

    @Override
    public void onConnectionFailed(String message) {

    }

    @Override
    public void onDisconnected(String message) {

    }

    @Override
    public void onDataReceived(String data) {

    }

    @Override
    public void startConnectedThread() {

    }

    @Override
    public void send(String message) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public BroadcastReceiver getReceiver() {
        return null;
    }

    @Override
    public IntentFilter getIntentFilter() {
        return null;
    }

    @Override
    public void closeAllThreads() {

    }
}
