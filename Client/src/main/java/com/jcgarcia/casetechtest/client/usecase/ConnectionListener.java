package com.jcgarcia.casetechtest.client.usecase;

import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public interface ConnectionListener {
  // Función que será llamada cuando se establezca una conexión
  void onConnected(BluetoothSocket socket);

  // Función que será llamada si hay un fallo en el
  // establecimiento de la conexión
  void onConnectionFailed(final String message);

  // Función que será llamada cuando se finalice la conexión
  void onDisconnected(final String message);

  void onDataReceived(String data);


  // These should be in another interface but to be faster I'll bypass the
  // Interface Segregation principle for  this time :)
  void startConnectedThread();
  
  void send(String message);

  void disconnect();

  boolean isConnected();

  BroadcastReceiver getReceiver();

  IntentFilter getIntentFilter();

  void closeAllThreads();
}