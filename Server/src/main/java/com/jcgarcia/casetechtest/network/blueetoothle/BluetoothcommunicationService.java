package com.jcgarcia.casetechtest.network.blueetoothle;

import android.app.Service;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.network.entity.Message;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothcommunicationService extends Service implements ClientConnection {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnected(BluetoothSocket socket) {

    }

    @Override
    public void onConnectionFailed(String idMessage) {

    }

    @Override
    public void onDisconnected(String idMessage) {

    }

    @Override
    public void onStateChanged(int idStringState) {

    }

    @Override
    public void onMessageReceived(Message message) {

    }
}
