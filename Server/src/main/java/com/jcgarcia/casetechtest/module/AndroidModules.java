package com.jcgarcia.casetechtest.module;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.network.ClientConnection;
import com.jcgarcia.casetechtest.network.bluetooth.BluetoothCommunicationService;
import com.jcgarcia.casetechtest.network.bluetooth.BluetoothConnBroadcastReceiver;
import com.jcgarcia.casetechtest.ui.activity.InterfaceOnlyActivity;
import com.jcgarcia.casetechtest.ui.activity.MainActivity;
import com.owlike.genson.Genson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 24/1/17.
 */


@Module(library = true,
        injects = {CaseTechTestApp.class, MainActivity.class, BluetoothConnBroadcastReceiver.class,
                InterfaceOnlyActivity.class},
        complete = false)
public class AndroidModules {

    private final Context context;

    public AndroidModules(Application application) {
        validateApplication(application);
        this.context = application;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    public TelephonyManager providesTelephonyManager() {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Provides
    public BluetoothCommunicationService providesBluetoothCommunicationService() {
        return new BluetoothCommunicationService();
    }

    @Provides
    public ClientConnection<BluetoothDevice> providesClientConnectionBluetoothDevice() {
        return new BluetoothCommunicationService();
    }

    @Provides
    public Genson providesGenson(){
        return new Genson();
    }


    private void validateApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("The Application passed in construction can't be null.");
        }
    }
}
