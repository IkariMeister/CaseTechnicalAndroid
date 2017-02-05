package com.jcgarcia.casetechtest.network.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.network.entity.Message;

import static android.content.ContentValues.TAG;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothComunicationService extends Service implements ClientConnection<BluetoothSocket> {


    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        sendServiceRunningBroadcast();
        Log.d(TAG, "Arrancado Servicio");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnected(BluetoothSocket socket) {
        Log.d(TAG, "onConnected");
        EstresContext.instance().setBluetoothConnected(true);
        connectedThread = new BluetoothConnectedThread(socket, this);
        connectedThread.start();
        connectedThread.sendDeviceConnected();
        sendOnConnectBroadcast();

    }

    @Override
    public void onConnectionFailed(String idMessage) {
        sendOnDisconnectBroadcast(idMessage);
        clientConnectionThread = null;
    }

    @Override
    public void onDisconnected(String idMessage) {
        EstresContext.instance().setBluetoothConnected(false);
        sendOnDisconnectBroadcast(idMessage);
        connectedThread = null;

    }

    @Override
    public void onStateChanged(int idStringState) {
        sendOnStateChangedBroadcast(idStringState);

    }

    @Override
    public void onMessageReceived(Message message) {
        //TODO process message to start use case
    }

    @Override
    public void onDestroy() {
        closeAllThreads();
        unregisterReceiver(bluetoothConnectionReceiver);
        Log.d(TAG, "Parado Servicio");
        super.onDestroy();
    }

    private void closeAllThreads() {

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
