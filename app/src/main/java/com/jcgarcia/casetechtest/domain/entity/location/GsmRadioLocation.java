package com.jcgarcia.casetechtest.domain.entity.location;

import com.jcgarcia.casetechtest.domain.entity.RadioLocation;

/**
 * Created by jcgarcia on 29/1/17.
 */

public class GsmRadioLocation extends RadioLocation {
    private int psc;
    private int cid;
    private int lac;

    public int getPsc() {
        return psc;
    }

    public void setPsc(int psc) {
        this.psc = psc;
    }

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
}
