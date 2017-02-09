package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class RadioDataSourceFactory {

    public static final int RADIO_ON = 1;
    public static final int RADIO_OFF = 0;

    private static RadioDataSourceFactory instance;

    private int type;

    private CaseTechTestApp context;

    public static RadioDataSourceFactory getInstance() {
        if (instance == null) {
            instance = new RadioDataSourceFactory();
        }
        return instance;
    }

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
                return new TelephonyManagerDataSource(context);
            default:
                return new FakeRadioDataSource();
        }
    }

    public void setContext(CaseTechTestApp context) {
        if(this.context==null)
            this.context = context;
    }
}
