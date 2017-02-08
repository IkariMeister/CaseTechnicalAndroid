package com.jcgarcia.casetechtest.network.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.network.ClientConnection;

import java.io.IOException;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothClientConnectionThread extends Thread {
    private final ClientConnection bluetoothConnectionListener;
    private final BluetoothSocket socket;

    public BluetoothClientConnectionThread(BluetoothDevice device,
                                  ClientConnection bluetoothConnectionListener) {

        this.bluetoothConnectionListener = bluetoothConnectionListener;
        BluetoothSocket socketTemp = null;
        try {
            socketTemp = device.createRfcommSocketToServiceRecord(Const.THE_UUID);
        } catch (IOException e) {
            bluetoothConnectionListener.onConnectionFailed(
                    "No se pudo crear el socket: " + e.getMessage());
        }

        socket = socketTemp;
    }

    public void run() {
        try {
            socket.connect();

            bluetoothConnectionListener.onConnected(socket);
        } catch (IOException e) {
            bluetoothConnectionListener.onConnectionFailed("Error al conectar: " + e.getMessage());
            try {
                socket.close();
            } catch (IOException e1) {
            }
        }
    }

    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
        }
        bluetoothConnectionListener.onConnectionFailed("Se canceló el hilo");
    }

    public BluetoothSocket getSocket() {
        return socket;
    }
}
