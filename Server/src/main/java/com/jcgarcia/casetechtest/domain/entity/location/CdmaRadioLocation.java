package com.jcgarcia.casetechtest.domain.entity.location;

import com.jcgarcia.casetechtest.domain.entity.RadioLocation;

/**
 * Created by jcgarcia on 29/1/17.
 */

public class CdmaRadioLocation extends RadioLocation {
    private int baseStationId;
    private int baseStationLongitude;
    private int baseStationLatitude;
    private int networkId;
    private int systemId;

    public int getBaseStationId() {
        return baseStationId;
    }

    public void setBaseStationId(int baseStationId) {
        this.baseStationId = baseStationId;
    }

    public int getBaseStationLongitude() {
        return baseStationLongitude;
    }

    public void setBaseStationLongitude(int baseStationLongitude) {
        this.baseStationLongitude = baseStationLongitude;
    }

    public int getBaseStationLatitude() {
        return baseStationLatitude;
    }

    public void setBaseStationLatitude(int baseStationLatitude) {
        this.baseStationLatitude = baseStationLatitude;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
