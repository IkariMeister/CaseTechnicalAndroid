package com.jcgarcia.casetechtest.domain.entity;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class RadioInfo {


    private int cell;
    private String carrier;
    private String tech;
    private SignalLevel signalLevel;
    private RadioLocation location;

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public SignalLevel getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(SignalLevel signalLevel) {
        this.signalLevel = signalLevel;
    }

    public RadioLocation getLocation() {
        return location;
    }

    public void setLocation(RadioLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RadioInfo{" +
                "cell=" + cell +
                ", carrier='" + carrier + '\'' +
                ", tech=" + tech +
                ", signalLevel=" + signalLevel +
                ", location=" + location +
                '}';
    }
}
