package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.gsm.GsmCellLocation;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.domain.entity.location.GsmRadioLocation;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class GsmCellLocationReader extends Mapper<GsmCellLocation, GsmRadioLocation> {

    @Override
    public GsmRadioLocation map(GsmCellLocation value) {
        GsmRadioLocation result = new GsmRadioLocation();
        result.setPsc(value.getPsc());
        result.setCid(value.getCid());
        result.setLac(value.getLac());
        return result;
    }

    @Override
    public GsmCellLocation reverseMap(GsmRadioLocation value) throws Exception {
        throw new Exception();
    }
}
