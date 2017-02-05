package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class RadioDataSourceFactory {

    public static final int RADIO_ON = 1;
    public static final int RADIO_OFF = 0;

    private int type;

    public RadioDataSourceFactory() {
        type = RADIO_ON;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DataSource<RadioInfo> getDataSource() {
        switch (type){
            case 0:
                return new FakeRadioDataSource();
            case 1:
                return new TelephonyManagerDataSource();
            default:
                return new FakeRadioDataSource();
        }
    }
}
