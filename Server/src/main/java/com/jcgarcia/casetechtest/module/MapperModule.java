package com.jcgarcia.casetechtest.module;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;
import com.jcgarcia.casetechtest.mapper.JsonRadioInfoMapper;
import com.owlike.genson.Genson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 25/1/17.
 */

@Module(library = true)
public class MapperModule {

    @Provides
    @Singleton
    public Mapper<RadioInfo, String> providesJasonRadioInfoMapper() {
        return new JsonRadioInfoMapper();
    }

    @Provides
    public Genson providesGeson(){
        return new Genson();
    }
}
