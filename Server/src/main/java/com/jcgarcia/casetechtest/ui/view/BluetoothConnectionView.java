package com.jcgarcia.casetechtest.ui.view;

/**
 * Created by jcgarcia on 8/2/17.
 */

public interface BluetoothConnectionView {

    void onBluetoothConnect();

    void onBluetoothDisconnect(String message);

    void onBluetoothStateChanged(int idStringState);
}
