package com.jcgarcia.casetechtest.datasource;

import android.telephony.CellInfo;
import android.telephony.TelephonyManager;

import com.jcgarcia.casetechtest.datasource.location.LocationReaderFactory;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class TelephonyManagerDataSource implements DataSource<RadioInfo> {

    @Inject
    TelephonyManager tm;
    @Inject
    LocationReaderFactory lrf;
    @Override
    public RadioInfo getCurrent() throws Exception {
        RadioInfo result = new RadioInfo();
        result.setLocation(lrf.read(tm.getCellLocation()));
        result.setCarrier(tm.getNetworkOperatorName());
        for (CellInfo info:tm.getAllCellInfo()) {

        }
        result.
        return result;
    }
}
