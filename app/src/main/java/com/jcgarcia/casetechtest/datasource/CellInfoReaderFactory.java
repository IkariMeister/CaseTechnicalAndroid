package com.jcgarcia.casetechtest.datasource;

import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;

import com.jcgarcia.casetechtest.datasource.cellinfo.CdmaRadioInfoReader;
import com.jcgarcia.casetechtest.datasource.cellinfo.GsmRadioInfoReader;
import com.jcgarcia.casetechtest.datasource.cellinfo.LteRadioInfoReader;
import com.jcgarcia.casetechtest.datasource.cellinfo.WcdmaRadioInfoReader;
import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class CellInfoReaderFactory {

    public RadioInfo read(CellInfo cellInfo) {
        if (cellInfo instanceof CellInfoGsm) {
            return new GsmRadioInfoReader().map((CellInfoGsm) cellInfo);
        }
        if (cellInfo instanceof CellInfoLte) {
            return new LteRadioInfoReader().map((CellInfoLte) cellInfo);
        }
        if (cellInfo instanceof CellInfoCdma) {
            return new CdmaRadioInfoReader().map((CellInfoCdma) cellInfo);
        }
        if (cellInfo instanceof CellInfoWcdma) {
            return new WcdmaRadioInfoReader().map((CellInfoWcdma) cellInfo);
        }
        return null;
    }
}
