package com.jcgarcia.casetechtest.domain.entity.radioinfo;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

/**
 * Created by jcgarcia on 26/1/17.
 */

public class GsmRadioInfo extends RadioInfo {
    private int cid;
    private int lac;
    private int mcc;
    private int mnc;
    private int psc;
    private int arfcn;
    private int bsic;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public int getPsc() {
        return psc;
    }

    public void setPsc(int psc) {
        this.psc = psc;
    }

    public int getArfcn() {
        return arfcn;
    }

    public void setArfcn(int arfcn) {
        this.arfcn = arfcn;
    }

    public int getBsic() {
        return bsic;
    }

    public void setBsic(int bsic) {
        this.bsic = bsic;
    }

    @Override
    public String toString() {
        return "GsmRadioInfo{" +
                "cid=" + cid +
                ", lac=" + lac +
                ", mcc=" + mcc +
                ", mnc=" + mnc +
                ", psc=" + psc +
                ", arfcn=" + arfcn +
                ", bsic=" + bsic +
                '}';
    }
}
