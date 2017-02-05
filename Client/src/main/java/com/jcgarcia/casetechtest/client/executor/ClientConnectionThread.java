package com.jcgarcia.casetechtest.client.executor;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.jcgarcia.casetechtest.client.usecase.ConnectionListener;
import com.jcgarcia.casetechtest.contract.common.Const;

import java.io.IOException;

public class ClientConnectionThread extends Thread {
  private final ConnectionListener connectionListener;
  private final BluetoothSocket socket;

  public ClientConnectionThread(BluetoothDevice device, ConnectionListener connectionListener) {
    // Guardo el escuchador de conexión
    this.connectionListener = connectionListener;
    BluetoothSocket socketTemp = null;
    try {
      socketTemp = device.createInsecureRfcommSocketToServiceRecord(Const.THE_UUID);
    } catch (IOException e) {
      connectionListener.onConnectionFailed("No se pudo crear el socket: " + e.getMessage());
    }
    // Guardo el socket
    socket = socketTemp;
  }

  public void run() {
    try {
      socket.connect();

      // Indico que se conectó
      connectionListener.onConnected(socket);
    } catch (IOException e) {
      connectionListener.onConnectionFailed("Error al conectar: " + e.getMessage());
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
    connectionListener.onConnectionFailed("Se canceló el hilo");
  }
}
