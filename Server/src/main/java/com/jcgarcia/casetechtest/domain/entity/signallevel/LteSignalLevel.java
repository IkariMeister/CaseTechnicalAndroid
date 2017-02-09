package com.jcgarcia.casetechtest.domain.entity.signallevel;

import com.jcgarcia.casetechtest.domain.entity.SignalLevel;

/**
 * Created by jcgarcia on 29/1/17.
 */

public class LteSignalLevel extends SignalLevel {
    private int timingAdvance;

    public int getTimingAdvance() {
        return timingAdvance;
    }

    public void setTimingAdvance(int timingAdvance) {
        this.timingAdvance = timingAdvance;
    }

    @Override
    public String toString() {
        return "LteSignalLevel{" +
                "timingAdvance=" + timingAdvance +
                '}';
    }
}
