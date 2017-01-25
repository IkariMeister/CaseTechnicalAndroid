package com.jcgarcia.casetechtest.domain.entity;

import com.jcgarcia.casetechtest.domain.common.RadioTech;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class RadioInfo {

    private String cell;
    private String carrier;
    private RadioTech tech;
    private String signalLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadioInfo radioInfo = (RadioInfo) o;

        if (cell != null ? !cell.equals(radioInfo.cell) : radioInfo.cell != null) return false;
        if (carrier != null ? !carrier.equals(radioInfo.carrier) : radioInfo.carrier != null)
            return false;
        if (tech != radioInfo.tech) return false;
        return signalLevel != null ? signalLevel.equals(radioInfo.signalLevel) : radioInfo.signalLevel == null;

    }

    @Override
    public int hashCode() {
        int result = cell != null ? cell.hashCode() : 0;
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (tech != null ? tech.hashCode() : 0);
        result = 31 * result + (signalLevel != null ? signalLevel.hashCode() : 0);
        return result;
    }
}
