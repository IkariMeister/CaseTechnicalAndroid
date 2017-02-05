package com.jcgarcia.casetechtest.network;

import android.bluetooth.BluetoothSocket;

import com.jcgarcia.casetechtest.network.entity.Message;

/**
 * Created by jcgarcia on 24/1/17.
 */

public interface ClientConnection<T1> {
    /**
     * This interface should be implemented by the class which implements the socket
     */
    void onConnected(T1 socket);

    void onConnectionFailed(final String idMessage);

    void onDisconnected(final String idMessage);

    void onStateChanged(int idStringState);

    void onMessageReceived(Message message);
}
