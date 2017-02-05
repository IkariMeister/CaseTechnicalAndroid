package com.jcgarcia.casetechtest.datasource.cellinfo;

import android.annotation.TargetApi;
import android.os.Build;
import android.telephony.CellInfoGsm;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.domain.entity.radioinfo.GsmRadioInfo;
import com.jcgarcia.casetechtest.domain.entity.signallevel.GsmSignalLevel;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class GsmRadioInfoReader extends Mapper<CellInfoGsm, GsmRadioInfo> {

    @TargetApi(24)
    private void fillNewerSdkData(GsmRadioInfo result, CellInfoGsm cellinfo) {
        result.setArfcn(cellinfo.getCellIdentity().getArfcn());
        result.setBsic(cellinfo.getCellIdentity().getBsic());
    }

    @Override
    public GsmRadioInfo map(CellInfoGsm value) {
        GsmRadioInfo result = new GsmRadioInfo();
        if (Build.VERSION.SDK_INT >= 24) {
            fillNewerSdkData(result, value);
        }
        GsmSignalLevel signalLevel = new GsmSignalLevel();
        result.setCid(value.getCellIdentity().getCid());
        result.setLac(value.getCellIdentity().getLac());
        result.setMcc(value.getCellIdentity().getMcc());
        result.setMnc(value.getCellIdentity().getMnc());
        result.setPsc(value.getCellIdentity().getPsc());
        signalLevel.setAsuLevel(value.getCellSignalStrength().getAsuLevel());
        signalLevel.setDbm(value.getCellSignalStrength().getDbm());
        signalLevel.setLevel(value.getCellSignalStrength().getLevel());
        result.setSignalLevel(signalLevel);
        return result;
    }

    @Override
    public CellInfoGsm reverseMap(GsmRadioInfo value) throws Exception {
        return null;
    }
}
