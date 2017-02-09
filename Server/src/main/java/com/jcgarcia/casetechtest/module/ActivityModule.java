package com.jcgarcia.casetechtest.module;

import com.jcgarcia.casetechtest.network.bluetooth.BluetoothHelper;
import com.jcgarcia.casetechtest.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 8/2/17.
 */

@Module(library = true, injects = MainActivity.class, complete = false)
public class ActivityModule {


    public ActivityModule() {
    }

    @Provides
    @Singleton
    public BluetoothHelper providesBluetoothHelper() {
        return new BluetoothHelper();
    }

//    @Provides
//    public MainPresenter providesMainPresenter(PresenterImpl.BaseView view){
//        return new MainPresenter(view,);
//    }
}
