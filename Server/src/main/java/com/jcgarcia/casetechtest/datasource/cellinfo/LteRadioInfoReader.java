package com.jcgarcia.casetechtest.datasource.cellinfo;

import android.annotation.TargetApi;
import android.os.Build;
import android.telephony.CellInfoLte;

import com.jcgarcia.casetechtest.contract.mapper.Mapper;
import com.jcgarcia.casetechtest.domain.entity.radioinfo.LteRadioInfo;
import com.jcgarcia.casetechtest.domain.entity.signallevel.LteSignalLevel;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class LteRadioInfoReader extends Mapper<CellInfoLte, LteRadioInfo> {

    @TargetApi(24)
    private void fillNewerSdkData(LteRadioInfo result, CellInfoLte cellinfo) {
        result.setEarfcn(cellinfo.getCellIdentity().getEarfcn());
    }

    @Override
    public LteRadioInfo map(CellInfoLte value) {
        LteRadioInfo result = new LteRadioInfo();
        result.setMcc(value.getCellIdentity().getMcc());
        result.setCi(value.getCellIdentity().getCi());
        if (Build.VERSION.SDK_INT >= 24) {
            fillNewerSdkData(result, value);
        }
        result.setPci(value.getCellIdentity().getPci());
        result.setMcc(value.getCellIdentity().getMnc());
        result.setTac(value.getCellIdentity().getTac());
        LteSignalLevel signalLevel = new LteSignalLevel();

        signalLevel.setLevel(value.getCellSignalStrength().getLevel());
        signalLevel.setDbm(value.getCellSignalStrength().getDbm());
        signalLevel.setAsuLevel(value.getCellSignalStrength().getAsuLevel());
        signalLevel.setTimingAdvance(value.getCellSignalStrength().getTimingAdvance());
        return result;
    }

    @Override
    public CellInfoLte reverseMap(LteRadioInfo value) throws Exception {
        throw new Exception();
    }
}
