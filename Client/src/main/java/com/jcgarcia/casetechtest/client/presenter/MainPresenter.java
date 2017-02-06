package com.jcgarcia.casetechtest.client.presenter;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import com.jcgarcia.casetechtest.client.usecase.BluetoothConnectionListener;
import com.jcgarcia.casetechtest.client.usecase.ConnectionListener;
import com.jcgarcia.casetechtest.client.usecase.FakeConnectionListener;
import com.jcgarcia.casetechtest.client.view.activity.MainActivity;
import com.jcgarcia.casetechtest.contract.common.MessageType;
import com.jcgarcia.casetechtest.contract.entity.NetworkMessage;
import com.jcgarcia.casetechtest.contract.view.presenter.PresenterImpl;
import com.owlike.genson.Genson;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class MainPresenter extends PresenterImpl<MainActivity> {


    public static final String NAME = "BluetoothConnectionExample";

    private Genson gs;

    final int REQUEST_DISCOVERABLE = 1;
    final int REQUEST_ENABLE_BLUETOOTH = 2;

    boolean sending = false;


    private ConnectionListener listener;



    public MainPresenter(MainActivity activity){
        super(activity);
        gs = new Genson();
//        listener = new FakeConnectionListener();
        listener = new BluetoothConnectionListener();
    }

    @Override
    public void onInitialize() {
        if (listener.getReceiver()!=null && listener.getIntentFilter()!=null) {

            view.registerReceiver(listener.getReceiver(),listener.getIntentFilter());
        }
    }

    @Override
    public void onStop() {

        onClickStopSend();
    }

    @Override
    public void onDestroy() {
        view.unregisterReceiver(listener.getReceiver());
        listener.closeAllThreads();
    }


    public void onClickServer() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
        view.startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
    }


    public void onClickStopSend(){
        sending = false;
        view.updateSendViews(sending);
    }

    public void onClickCloseConnection(){
        listener.disconnect();
    }

    public void onActivityResult(int requestCode, int resultCode) {
        switch (requestCode) {
            case REQUEST_DISCOVERABLE:
                if (resultCode != RESULT_CANCELED) {
                    view.updateState("Servidor esperando conexión...");
                    listener.startConnectedThread();
                }
                break;
            case REQUEST_ENABLE_BLUETOOTH:
                if (resultCode == RESULT_OK) {
                    openSelectDevice();
                }
                break;
        }
    }

    private void sendMessage(String message) {
        view.printLog(message);
        listener.send(message);
    }

    private void openSelectDevice() {
        view.startActivity(new Intent("android.bluetooth.devicepicker.action.LAUNCH").putExtra(
                "android.bluetooth.devicepicker.extra.NEED_AUTH", false)
                .putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 0)
                .setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS));
    }

    public void onClickGetRadio() {
        NetworkMessage message = new NetworkMessage(MessageType.RADIO_INFO,null);
        listener.send(gs.serialize(message));
    }

    public void onClickbuttonRegisterOn() {
        NetworkMessage message = new NetworkMessage(MessageType.REGISTER,new Boolean(true));
        listener.send(gs.serialize(message));
    }

    public void onClickbuttonRegisterOff() {

        NetworkMessage message = new NetworkMessage(MessageType.REGISTER,new Boolean(false));
        listener.send(gs.serialize(message));

    }

    public void updateState(String s) {
        view.updateState(s);
    }

    public void updateConnectUI() {
        view.updateConnectUI();
    }

    public void updateDisconnectUI(String message) {
        view.updateDisconnectUI(message);
    }

    public void dataReceived(String data) {
        view.printLog(data);
    }
}
