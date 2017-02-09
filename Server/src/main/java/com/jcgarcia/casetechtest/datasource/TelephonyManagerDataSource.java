package com.jcgarcia.casetechtest.datasource;

import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;

import com.jcgarcia.casetechtest.application.CaseTechTestApp;
import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.datasource.location.LocationReaderFactory;
import com.jcgarcia.casetechtest.domain.common.RadioTech;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;
import com.jcgarcia.casetechtest.domain.entity.RadioLocation;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class TelephonyManagerDataSource implements DataSource<RadioInfo> {

    @Inject
    TelephonyManager tm;
    @Inject
    LocationReaderFactory lrf;
    @Inject
    Mapper<Integer, RadioTech> mTechMapper;
    @Inject
    CellInfoReaderFactory cellInfoReaderFactory;

    public TelephonyManagerDataSource(CaseTechTestApp context) {
        context.inject(this);
    }
    @Override
    public RadioInfo getCurrent() throws Exception {
        RadioInfo result = new RadioInfo();
        RadioTech radiotech = mTechMapper.map(tm.getNetworkType());
        for (CellInfo cellinfo : tm.getAllCellInfo()) {
            result = cellInfoReaderFactory.read(cellinfo);
        }
        CellLocation location = tm.getCellLocation();
        result.setLocation((RadioLocation) lrf.getLocationReader(location).map(location));
        result.setCarrier(tm.getNetworkOperatorName());
        result.setTech(radiotech.toString());

        return result;
    }

}
