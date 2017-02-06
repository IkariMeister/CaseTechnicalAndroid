package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 24/1/17.
 */


public class FakeRadioDataSource implements DataSource<RadioInfo> {

    public FakeRadioDataSource(){}

    @Override
    public RadioInfo getCurrent() throws Exception {
        return new RadioInfo();
    }
}
