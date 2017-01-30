package com.jcgarcia.casetechtest.datasource.cellinfo;

import android.telephony.CellInfoCdma;

import com.jcgarcia.casetechtest.domain.entity.radioinfo.CdmaRadioInfo;
import com.jcgarcia.casetechtest.mapper.Mapper;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class CdmaRadioInfoReader extends Mapper<CellInfoCdma, CdmaRadioInfo> {

    @Override
    public CdmaRadioInfo map(CellInfoCdma value) {
        CdmaRadioInfo result = new CdmaRadioInfo();
        value.getCellIdentity().getBasestationId();
        value.getCellIdentity().getLatitude();
        value.getCellIdentity().getLongitude();
        value.getCellIdentity().getNetworkId();
        value.getCellIdentity().getSystemId();
        value.isRegistered();
        value.getCellSignalStrength().getAsuLevel();
        value.getCellSignalStrength().getLevel();
        value.getCellSignalStrength().getDbm();
        value.getCellSignalStrength().getCdmaDbm();
        value.getCellSignalStrength().getCdmaEcio();
        value.getCellSignalStrength().getCdmaLevel();
        value.getCellSignalStrength().getEvdoDbm();
        value.getCellSignalStrength().getEvdoEcio();
        value.getCellSignalStrength().getEvdoLevel();
        value.getCellSignalStrength().getEvdoSnr();
        return result;
    }

    @Override
    public CellInfoCdma reverseMap(CdmaRadioInfo value) throws Exception {
        return null;
    }
}
