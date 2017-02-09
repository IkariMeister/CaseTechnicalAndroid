package com.jcgarcia.casetechtest.module;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.datasource.CellInfoReaderFactory;
import com.jcgarcia.casetechtest.datasource.TelephonyManagerDataSource;
import com.jcgarcia.casetechtest.datasource.location.LocationReaderFactory;
import com.jcgarcia.casetechtest.domain.common.RadioTech;
import com.jcgarcia.casetechtest.mapper.NetworkTypeMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 30/1/17.
 */
@Module(library = true, complete = false,
        injects = TelephonyManagerDataSource.class)
public class TelephonyModule {


    @Provides
    @Singleton
    public LocationReaderFactory providesLocationReaderFactory() {
        return new LocationReaderFactory();
    }

    @Provides
    @Singleton
    public Mapper<Integer, RadioTech> providesNetworkTypeMapper() {
        return new NetworkTypeMapper();
    }

    @Provides
    @Singleton
    public CellInfoReaderFactory providesCellInfoReaderFactory() {
        return new CellInfoReaderFactory();
    }


}
