package com.jcgarcia.casetechtest.client.usecase;

import android.bluetooth.BluetoothSocket;

public interface ConnectionListener {
  // Función que será llamada cuando se establezca una conexión
  void onConnected(BluetoothSocket socket);

  // Función que será llamada si hay un fallo en el
  // establecimiento de la conexión
  void onConnectionFailed(final String message);

  // Función que será llamada cuando se finalice la conexión
  void onDisconnected(final String message);

  void onDataReceived(String data);
}