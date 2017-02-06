package com.jcgarcia.casetechtest.network.bluetooth;

import com.jcgarcia.casetechtest.network.ConnectedThreadable;
import com.jcgarcia.casetechtest.network.entity.Message;

import java.io.IOException;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothConnectedThread extends Thread implements ConnectedThreadable<Message>{
    @Override
    public void readNetworkMessage(Message message) {

    }

    @Override
    public void writeNetworkMessage(Message message) {

    }

    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
    public void run() {
        byte[] buffer = new byte[BluetoothMapper.TRAMA_SIZE];
        int readed;

        try {
            readed = readInputStream(buffer);
            while (readed != -1) {
                if (readed > 0) {
                    final byte temp[] = new byte[readed];
                    System.arraycopy(buffer, 0, temp, 0, readed);

                    TramaEntity tramaEntity = BluetoothMapper.getTrama(temp, getTimestamp());

                    if (tramaEntity != null) {
                        send(PATTERN_ACK);
                        procesarTrama(tramaEntity);
                        bluetoothConnectionListener.onTramaReceived(tramaEntity);
                    }

                    readed = readInputStream(buffer);
                }
            }
        } catch (IOException e) {
            errorDeLectura(e);
        } finally {
            // todo cerrar recursos
        }
    }
}
