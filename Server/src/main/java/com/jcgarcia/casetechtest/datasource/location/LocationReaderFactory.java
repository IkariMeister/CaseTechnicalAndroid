package com.jcgarcia.casetechtest.datasource.location;

import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.exception.CellLocationException;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class LocationReaderFactory {

    public Mapper getLocationReader(CellLocation location) throws CellLocationException {
        if(location instanceof GsmCellLocation){
            return new GsmCellLocationReader();
        } else if(location instanceof CdmaCellLocation){
            return new CdmaCellLocationReader();
        }
        throw new CellLocationException();
    }


}
