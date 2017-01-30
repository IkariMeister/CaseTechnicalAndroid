package com.jcgarcia.casetechtest.domain.entity.signallevel;

import com.jcgarcia.casetechtest.domain.entity.SignalLevel;

/**
 * Created by ikari on 29/1/17.
 */

public class LteSignalLevel extends SignalLevel {
    private int timingAdvance;

    public int getTimingAdvance() {
        return timingAdvance;
    }

    public void setTimingAdvance(int timingAdvance) {
        this.timingAdvance = timingAdvance;
    }
}
