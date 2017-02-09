package com.jcgarcia.casetechtest.domain.entity;

/**
 * Created by ikari on 29/1/17.
 */

public class SignalLevel {

    private int level;
    private int dbm;
    private int asuLevel;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDbm() {
        return dbm;
    }

    public void setDbm(int dbm) {
        this.dbm = dbm;
    }

    public int getAsuLevel() {
        return asuLevel;
    }

    public void setAsuLevel(int asuLevel) {
        this.asuLevel = asuLevel;
    }

    @Override
    public String toString() {
        return "SignalLevel{" +
                "level=" + level +
                ", dbm=" + dbm +
                ", asuLevel=" + asuLevel +
                '}';
    }
}
