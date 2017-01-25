package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.cdma.CdmaCellLocation;

/**
 * Created by ikari on 25/1/17.
 */

public class CdmaLocationReader implements LocationReader<CdmaCellLocation> {
    private static final String LOCATION_STRING = "Base Station id %s, latitude %s, longitude %s";

    @Override
    public String read(CdmaCellLocation location) {
        return String.format(LOCATION_STRING, location.getBaseStationId(),
                location.getBaseStationLatitude(), location.getBaseStationLongitude());
    }
}
