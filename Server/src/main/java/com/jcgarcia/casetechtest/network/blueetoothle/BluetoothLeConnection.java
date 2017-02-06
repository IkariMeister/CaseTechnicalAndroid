package com.jcgarcia.casetechtest.network.blueetoothle;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jcgarcia.casetechtest.contract.common.Const;
import com.jcgarcia.casetechtest.network.bluetooth.BluetoothConnectedThread;

import static android.content.ContentValues.TAG;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class BluetoothLeConnection extends BluetoothGattCallback {

    private Context mContext;
    private String mTrama;
    private BluetoothGatt mGatt;
    private BluetoothGattCharacteristic mCharacteristic;
    private BluetoothConnectedThread mConnectedThread;

    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status){
        if(status== BluetoothGatt.GATT_SUCCESS){
            String s = characteristic.getStringValue(0);
            byte[] bytevalue = s.getBytes();
            Log.e(TAG,this.mTrama);
            Log.e(TAG,Utilities.bytesToHex(bytevalue));
            this.mTrama="";
        }
    }


    @Override
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        super.onConnectionStateChange(gatt, status, newState);

        if(newState== BluetoothGatt.STATE_CONNECTED){
            this.mGatt = gatt;
            //sendConnectBroadcast(mContext,gatt.getDevice());
            gatt.discoverServices();
        }
        else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
            if (mContext!=null) {
                sendDisconnectBroadcast(mContext);
            }

        }
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        if (status == BluetoothGatt.GATT_SUCCESS) {
            BluetoothGattService service = gatt.getService(Const.BLE_SERVICE_UUID);
            this.mCharacteristic = service.getCharacteristic(Const.BLE_CHARACTERISTIC_UUID);
            gatt.setCharacteristicNotification(mCharacteristic, true);
            if (this.mContext!=null) {
                sendConnectBroadcast(mContext,gatt.getDevice());
            }
        }
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        this.mTrama+=characteristic.getStringValue(0);
        if (this.mTrama.length()>= BluetoothMapper.TRAMA_SIZE*2) {
            Log.e("TRAMA",this.mTrama);
            if (this.mConnectedThread!=null) {
                this.mConnectedThread.processMessage(this.mTrama);
            }
        }
    }

    public void sendConnectBroadcast(Context context, BluetoothDevice bluetoothDevice) {
        Intent intent = BluetoothCommunicationService.buildBluetoothConnectIntent(bluetoothDevice);
        context.sendBroadcast(intent);
    }

}
