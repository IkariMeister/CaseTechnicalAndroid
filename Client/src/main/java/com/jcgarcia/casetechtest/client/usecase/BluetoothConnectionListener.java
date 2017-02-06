package com.jcgarcia.casetechtest.client.usecase;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jcgarcia.casetechtest.client.executor.ClientConnectionThread;
import com.jcgarcia.casetechtest.client.executor.ConnectedThread;
import com.jcgarcia.casetechtest.client.executor.ServerConnectionThread;
import com.jcgarcia.casetechtest.client.presenter.MainPresenter;

/**
 * Created by jcgarcia on 6/2/17.
 */

public class BluetoothConnectionListener implements ConnectionListener {

    ServerConnectionThread serverConnectionThread;
    ClientConnectionThread clientConnectionThread;
    ConnectedThread connectedThread;
//    this should be an interface like bluetooth presenter or socket presenter with the specific methods
//    but to be faster I'll bypass the Dependecy Inversion principle for  this time :)
    MainPresenter presenter;

    public BroadcastReceiver bluetoothDeviceSelectedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (clientConnectionThread != null) {
                clientConnectionThread.cancel();
            }
            presenter.updateState("Cliente esperando conexión...");

            clientConnectionThread = new ClientConnectionThread(device,
                    BluetoothConnectionListener.this);
            clientConnectionThread.start();
        }
    };
    @Override
    public void onConnected(BluetoothSocket socket) {
        presenter.updateConnectUI();
        connectedThread = new ConnectedThread(socket, this);
        connectedThread.start();
    }

    @Override
    public void onConnectionFailed(String message) {
        presenter.updateDisconnectUI(message);
        serverConnectionThread = null;
        clientConnectionThread = null;
    }

    @Override
    public void onDisconnected(String message) {
        presenter.updateDisconnectUI(message);
        connectedThread = null;
    }

    @Override
    public void onDataReceived(String data) {
        presenter.dataReceived(data);
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
        return bluetoothDeviceSelectedReceiver;
    }

    @Override
    public IntentFilter getIntentFilter() {
        return new IntentFilter("android.bluetooth.devicepicker.action.DEVICE_SELECTED");
    }

    public void closeAllThreads() {

        if (serverConnectionThread != null) {
            serverConnectionThread.cancel();
            serverConnectionThread = null;
        }

        if (clientConnectionThread != null) {
            clientConnectionThread.cancel();
            clientConnectionThread = null;
        }

        if (connectedThread != null) {
            connectedThread.cancel();
            connectedThread = null;
        }

    }
}
