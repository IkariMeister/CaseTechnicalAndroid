package com.jcgarcia.casetechtest.module;

import com.jcgarcia.casetechtest.GetRadio;
import com.jcgarcia.casetechtest.Register;
import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 25/1/17.
 */

@Module(library = true)
public class UseCaseModule {

    @Provides
    @Singleton
    public GetRadio providesGetRadio(){
        return new GetRadio();
    }

    @Provides
    @Singleton
    public Register providesRegister(){
        return new Register();
    }

    @Provides
    @Singleton
    public RadioDataSourceFactory providesRadioDSFactory(){
        return new RadioDataSourceFactory();
    }
}
