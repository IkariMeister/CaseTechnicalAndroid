package com.jcgarcia.casetechtest.contract.common;

import java.util.UUID;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class Const {
    public static final UUID BLE_SERVICE_UUID = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");

    public static final UUID BLE_CHARACTERISTIC_UUID = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");

    public static final UUID THE_UUID = UUID.fromString("42ad6984-fd66-4b11-900f-a52b454e34ae");

    public static final String NAME = "CaseOnIT Technical Test";

    public static final int REQUEST_ENABLE_BLUETOOTH = 2;

    public static final String ACTION_BLUETOOTH_ON_CONNECT = "ACTION_BLUETOOTH_ON_CONNECT";
    public static final String ACTION_BLUETOOTH_ON_DISCONNECT = "ACTION_BLUETOOTH_ON_DISCONNECT";
    public static final String ACTION_BLUETOOTH_ON_STATE_CHANGED = "ACTION_BLUETOOTH_ON_STATE_CHANGED";
    public static final String EXTRA_BLUETOOTH_DISCONNECT_MESSAGE = "EXTRA_BLUETOOTH_DISCONNECT_MESSAGE";
    public static final String EXTRA_BLUETOOTH_STATE_CHANGED_ID_STRING =
            "EXTRA_BLUETOOTH_STATE_CHANGED_ID_STRING";
    public static final String ACTION_DEVICE_SELECTED =
            "android.bluetooth.devicepicker.action.DEVICE_SELECTED";
}
