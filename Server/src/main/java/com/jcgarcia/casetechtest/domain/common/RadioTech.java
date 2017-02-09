package com.jcgarcia.casetechtest.domain.common;

/**
 * Created by jcgarcia on 24/1/17.
 */

public enum RadioTech {
    GSM ("GSM"),
    GPRS ("GPRS"),
    EDGE ("EDGE"),
    HSDPA ("HSDPA"),
    HSPA ("HSPA"),
    HSPAP ("HSPAP"),
    HSUPA ("HSUPA"),
    IDEN ("IDEN"),
    EHRPD ("EHRPD"),
    EVDO_0 ("EVDO0"),
    IWLAN ("IWLAN"),
    EVDO_A ("EVDOA"),
    EVDO_B ("EVDOB"),
    CDMA ("CDMA"),
    SCDMA ("SCDMA"),
    LTE ("LTE"),
    UMTS ("UMTS"),
    xRTT1 ("1xRTT"),
    UNKNOWN ("UNKNOWN");

    private final String name;

    private RadioTech(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
