package com.jcgarcia.casetechtest.network.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.network.entity.Message;

import static android.content.ContentValues.TAG;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothCommunicationService extends Service implements ClientConnection<BluetoothDevice> {

    private BluetoothClientConnectionThread clientConnectionThread;
    private BluetoothConnectedThread connectedThread;


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
    public void onConnected(BluetoothDevice device) {
        if (clientConnectionThread != null) {
            clientConnectionThread.cancel();
        }


        clientConnectionThread = new BluetoothClientConnectionThread(device,
                BluetoothCommunicationService.this);
        clientConnectionThread.start();
        BluetoothSocket socket = clientConnectionThread.getSocket();
        connectedThread = new BluetoothConnectedThread(socket, this);
        connectedThread.start();

        sendOnConnectBroadcast();
    }

    @Override
    public void onConnectionFailed(String idMessage) {
        sendOnDisconnectBroadcast(idMessage);
        clientConnectionThread = null;
    }

    @Override
    public void onDisconnected(String idMessage) {
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
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        closeAllThreads();
//        unregisterReceiver(bluetoothConnectionReceiver);
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
    private void registerReceiver() {
        IntentFilter intent = new IntentFilter(Const.ACTION_BLUETOOTH_CONNECT);
        intent.addAction(Const.ACTION_BLUETOOTH_DISCONNECT);
//        registerReceiver(bluetoothConnectionReceiver, intent);
    }

    private void sendOnConnectBroadcast() {
//        Intent intent = WearableConnectionActivity.buildBluetoothOnConnectIntent();
//        sendBroadcast(intent);
    }

    private void sendOnDisconnectBroadcast(String message) {
//        Intent intent = WearableConnectionActivity.buildBluetoothOnDisconnectIntent(message);
//        sendBroadcast(intent);
    }

    private void sendOnStateChangedBroadcast(int idMessage) {
//        Intent intent = WearableConnectionActivity.buildBluetoothOnStateChangedIntent(idMessage);
//        sendBroadcast(intent);
    }

    private void sendServiceRunningBroadcast() {
        Intent intent = new Intent(Const.ACTION_BLUETOOTH_SERVICE_RUNNING);
        sendBroadcast(intent);
    }

}
