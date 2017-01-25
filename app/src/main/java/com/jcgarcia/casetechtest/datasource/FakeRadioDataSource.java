package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jcgarcia on 24/1/17.
 */

@Singleton
public class FakeRadioDataSource implements DataSource<RadioInfo> {

    @Inject
    public FakeRadioDataSource(){}

    @Override
    public RadioInfo getCurrent() throws Exception {
        return new RadioInfo();
    }
}
