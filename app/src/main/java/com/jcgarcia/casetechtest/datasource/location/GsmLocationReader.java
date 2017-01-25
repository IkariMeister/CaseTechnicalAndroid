package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.gsm.GsmCellLocation;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class GsmLocationReader implements LocationReader<GsmCellLocation> {
    private static final String LOCATION_STRING = "Location area code %s, Cell id %s";

    @Override
    public String read(GsmCellLocation location) {
        return String.format(LOCATION_STRING,location.getLac(),location.getCid());
    }
}
