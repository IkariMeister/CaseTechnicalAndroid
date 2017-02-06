package com.jcgarcia.casetechtest.datasource.cellinfo;

import android.annotation.TargetApi;
import android.os.Build;
import android.telephony.CellInfoWcdma;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.domain.entity.radioinfo.WcdmaRadioInfo;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class WcdmaRadioInfoReader extends Mapper<CellInfoWcdma, WcdmaRadioInfo> {

    @TargetApi(24)
    private void fillNewerSdkData(CellInfoWcdma cellinfo) {
        cellinfo.getCellIdentity().getUarfcn();
    }

    @Override
    public WcdmaRadioInfo map(CellInfoWcdma value) {
        value.getCellIdentity().getCid();
        value.getCellIdentity().getLac();
        value.getCellIdentity().getMcc();
        value.getCellIdentity().getMnc();
        value.getCellIdentity().getPsc();
        if (Build.VERSION.SDK_INT >= 24) {
            fillNewerSdkData(value);
        }
        value.getCellSignalStrength().getDbm();
        value.getCellSignalStrength().getAsuLevel();
        value.getCellSignalStrength().getLevel();
        return null;
    }

    @Override
    public CellInfoWcdma reverseMap(WcdmaRadioInfo value) throws Exception {
        return null;
    }
}
