package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.cdma.CdmaCellLocation;

import com.jcgarcia.casetechtest.domain.entity.location.CdmaRadioLocation;
import com.jcgarcia.casetechtest.mapper.Mapper;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class CdmaCellLocationReader extends Mapper<CdmaCellLocation, CdmaRadioLocation> {

    @Override
    public CdmaRadioLocation map(CdmaCellLocation value) {
        CdmaRadioLocation result = new CdmaRadioLocation();
        result.setBaseStationId(value.getBaseStationId());
        result.setBaseStationLatitude(value.getBaseStationLatitude());
        result.setBaseStationLongitude(value.getBaseStationLongitude());
        result.setNetworkId(value.getNetworkId());
        result.setSystemId(value.getSystemId());
        return result;
    }

    @Override
    public CdmaCellLocation reverseMap(CdmaRadioLocation value) throws Exception {
        throw new Exception();
    }
}
