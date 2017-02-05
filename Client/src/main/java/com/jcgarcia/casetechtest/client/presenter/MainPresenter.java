package com.jcgarcia.casetechtest.client.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import com.jcgarcia.casetechtest.client.executor.ClientConnectionThread;
import com.jcgarcia.casetechtest.client.executor.ConnectedThread;
import com.jcgarcia.casetechtest.client.usecase.ConnectionListener;
import com.jcgarcia.casetechtest.client.usecase.GeneradorTramasAleatorias;
import com.jcgarcia.casetechtest.client.view.activity.MainActivity;
import com.jcgarcia.casetechtest.client.executor.ServerConnectionThread;
import com.jcgarcia.casetechtest.contract.view.presenter.PresenterImpl;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class MainPresenter extends PresenterImpl<MainActivity> implements ConnectionListener {


    public static final String NAME = "BluetoothConnectionExample";

    final int REQUEST_DISCOVERABLE = 1;
    final int REQUEST_ENABLE_BLUETOOTH = 2;

    boolean sending = false;
    ServerConnectionThread serverConnectionThread;
    ClientConnectionThread clientConnectionThread;
    ConnectedThread connectedThread;

    private Handler mTempoHandler;
    private Temporizacion tareaEnvioTrama;

    public BroadcastReceiver bluetoothDeviceSelectedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (clientConnectionThread != null) {
                clientConnectionThread.cancel();
            }

            view.updateState("Cliente esperando conexión...");

            clientConnectionThread = new ClientConnectionThread(device, MainPresenter.this);
            clientConnectionThread.start();
        }
    };

    public MainPresenter(MainActivity activity){
        super(activity);
    }

    @Override
    public void onInitialize() {
        view.registerReceiver(bluetoothDeviceSelectedReceiver,
                new IntentFilter("android.bluetooth.devicepicker.action.DEVICE_SELECTED"));

        mTempoHandler = new Handler();
        tareaEnvioTrama = new Temporizacion();
    }

    @Override
    public void onStop() {

        onClickStopSend();
    }

    @Override
    public void onDestroy() {

        view.unregisterReceiver(bluetoothDeviceSelectedReceiver);
        closeAllThreads();
        mTempoHandler.removeCallbacks(tareaEnvioTrama);
        tareaEnvioTrama = null;
        mTempoHandler = null;
    }

    @Override
    public void onConnected(BluetoothSocket socket) {
        view.runOnUiThread(new Runnable() {
            public void run() {
                view.updateConnectUI();
            }
        });
        connectedThread = new ConnectedThread(socket, this);
        connectedThread.start();
    }

    @Override
    public void onConnectionFailed(String message) {

                view.updateDisconnectUI(message);
        serverConnectionThread = null;
        clientConnectionThread = null;
    }

    @Override
    public void onDisconnected(final String message) {
        view.runOnUiThread(new Runnable() {
            public void run() {
                view.updateDisconnectUI(message);
            }
        });
        connectedThread = null;
    }

    @Override
    public void onDataReceived(String data) {
        view.printLog(data);
    }


    public void onClickServer() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
        view.startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
    }

    public void onClickSend(){
        if (connectedThread != null) {

            sending = true;

            view.updateSendViews(sending);

            sendMessage();
        }
    }

    public void onClickStopSend(){
        sending = false;
        view.updateSendViews(sending);
    }

    public void onClickCloseConnection(){
        closeAllThreads();
    }

    public void onActivityResult(int requestCode, int resultCode) {
        switch (requestCode) {
            case REQUEST_DISCOVERABLE:
                if (resultCode != RESULT_CANCELED) {
                    view.updateState("Servidor esperando conexión...");
                    serverConnectionThread = new ServerConnectionThread(this);
                    serverConnectionThread.start();
                }
                break;
            case REQUEST_ENABLE_BLUETOOTH:
                if (resultCode == RESULT_OK) {
                    openSelectDevice();
                }
                break;
        }
    }

    private void sendMessage() {
        String message = "";
        view.printLog(message);
        connectedThread.send(message);
        programaEnvio();
    }

    private void programaEnvio() {
        mTempoHandler.postDelayed(tareaEnvioTrama, GeneradorTramasAleatorias.SEGUNDOS_POR_TRAMA * 1000);
    }

    private void closeAllThreads() {

        if (serverConnectionThread != null) {
            serverConnectionThread.cancel();
            serverConnectionThread = null;
        }

        if (clientConnectionThread != null) {
            clientConnectionThread.cancel();
            clientConnectionThread = null;
        }

        if (connectedThread != null) {
            connectedThread.cancel();
            connectedThread = null;
        }

        onDestroy();
    }

    public void onClickClient() {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            view.startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH);
            return;
        }
        // Abro la actividad para seleccionar un dispositivo
        openSelectDevice();
    }

    private void openSelectDevice() {
        view.startActivity(new Intent("android.bluetooth.devicepicker.action.LAUNCH").putExtra(
                "android.bluetooth.devicepicker.extra.NEED_AUTH", false)
                .putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 0)
                .setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS));
    }



    public class Temporizacion implements Runnable {

        @Override
        public void run() {
            if (sending) {
                sendMessage();
            }
        }
    }
}
