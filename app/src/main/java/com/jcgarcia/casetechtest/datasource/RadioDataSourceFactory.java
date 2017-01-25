package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class RadioDataSourceFactory {

    public DataSource<RadioInfo> getDataSource(int type){
        switch (type){
            case 0:
                return new FakeRadioDataSource();
            default:
                return new FakeRadioDataSource();
        }
    }
}
