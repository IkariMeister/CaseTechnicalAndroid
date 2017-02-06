package com.jcgarcia.casetechtest.network.bluetooth;

import android.accounts.NetworkErrorException;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.network.ConnectedThreadable;
import com.jcgarcia.casetechtest.network.entity.Message;
import com.owlike.genson.Genson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothConnectedThread extends Thread implements ConnectedThreadable<Message> {

    private ClientConnection<BluetoothSocket> bluetoothConnectionListener;
    private final BluetoothSocket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    @Inject
    Genson gs;

    public BluetoothConnectedThread(BluetoothSocket socket,
                                    ClientConnection<BluetoothSocket> bluetoothConnectionListener) {

        this.socket = socket;
        this.bluetoothConnectionListener = bluetoothConnectionListener;
        InputStream inTemp = null;
        OutputStream outTemp = null;
        try {
            inTemp = socket.getInputStream();
            outTemp = socket.getOutputStream();
        } catch (IOException e) {
            bluetoothConnectionListener.onDisconnected(
                    "No se pueden abrir los canales de entrada y salida: " + e.getMessage());
        }

        inputStream = inTemp;
        outputStream = outTemp;

    }

    @Override
    public void readNetworkMessage() {
        try {
            String message;
            do {
                message = readInputStream();
                try {
                    Message m = gs.deserialize(message, Message.class);
                    bluetoothConnectionListener.onMessageReceived(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        } finally {
            // todo cerrar recursos
        }
    }

    @Override
    public void writeNetworkMessage(Message message) {
        String jsonMessage = gs.serialize(message);
        try {
            outputStream.write(jsonMessage.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    public void run() {
        readNetworkMessage();

    }

    private String readInputStream() throws IOException, NetworkErrorException {
        String result;
        if (socket.isConnected()) {
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            result = input.readLine();
        } else {
            throw new NetworkErrorException();
        }
        return result;
    }
}
