package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.jcgarcia.casetechtest.exception.CellLocationException;

/**
 * Created by ikari on 25/1/17.
 */

public class LocationReaderFactory {

    public LocationReader getLocationReader(CellLocation location) throws CellLocationException {
        if(location instanceof GsmCellLocation){
            return new GsmLocationReader();
        } else if(location instanceof CdmaCellLocation){
            return new CdmaLocationReader();
        }
        throw new CellLocationException();
    }

    public String read(CellLocation location) throws CellLocationException {
        return this.getLocationReader(location).read(location);
    }
}
