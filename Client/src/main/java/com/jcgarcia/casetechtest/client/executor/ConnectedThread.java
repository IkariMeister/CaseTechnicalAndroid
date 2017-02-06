package com.jcgarcia.casetechtest.client.executor;

import android.bluetooth.BluetoothSocket;

import com.jcgarcia.casetechtest.client.usecase.ConnectionListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ConnectedThread extends Thread {

  private final BluetoothSocket socket;
  private final InputStream inputStream;
  private final OutputStream outputStream;
  private ConnectionListener connectionListener;

  public ConnectedThread(BluetoothSocket socket,
                         ConnectionListener connectionListener) {

    this.socket = socket;
    this.connectionListener = connectionListener;

    InputStream inTemp = null;
    OutputStream outTemp = null;
    try {
      inTemp = socket.getInputStream();
      outTemp = socket.getOutputStream();
    } catch (IOException e) {
      connectionListener.onDisconnected(
          "No se pueden abrir los canales de entrada y salida: " + e.getMessage());
    }

    inputStream = inTemp;
    outputStream = outTemp;
  }

  public void run() {
    final byte[] buffer = new byte[2];
    int readed;
    try {
      readed = inputStream.read(buffer);
      while (readed != -1) {
        if (readed > 0) {
          String str = new String(buffer, StandardCharsets.UTF_8);
          connectionListener.onDataReceived(str);
          readed = inputStream.read(buffer);
        }
      }
    } catch (IOException e) {
      connectionListener.onDisconnected("Error al leer: " + e.getMessage());
    }
  }

  public void cancel() {
    try {
      socket.close();
    } catch (IOException e) {
    }
  }

  public void send(byte[] buffer) {
    try {
      outputStream.write(buffer);
    } catch (IOException e) {
      connectionListener.onDisconnected("Error al escribir: " + e.getMessage());
    }
  }
  public void send(String buffer) {
    try {
      outputStream.write(buffer.getBytes());
    } catch (IOException e) {
      connectionListener.onDisconnected("Error al escribir: " + e.getMessage());
    }
  }
}
