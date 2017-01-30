package com.jcgarcia.casetechtest.mapper;

import android.os.Build;
import android.telephony.TelephonyManager;

import com.jcgarcia.casetechtest.domain.common.RadioTech;

/**
 * Created by jcgarcia on 29/1/17.
 */


public class NetworkTypeMapper extends Mapper<Integer, RadioTech> {
    @Override
    public RadioTech map(Integer value) {
        RadioTech result;
        switch (value) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                result = RadioTech.xRTT1;
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                result = RadioTech.CDMA;
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                result = RadioTech.EDGE;
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                result = RadioTech.EHRPD;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                result = RadioTech.EVDO_0;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                result = RadioTech.EVDO_A;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                result = RadioTech.EVDO_B;
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                result = RadioTech.GPRS;
                break;
            case TelephonyManager.NETWORK_TYPE_GSM:
                result = RadioTech.GSM;
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                result = RadioTech.HSDPA;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                result = RadioTech.HSPA;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                result = RadioTech.HSPAP;
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                result = RadioTech.HSUPA;
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                result = RadioTech.IDEN;
                break;
            case TelephonyManager.NETWORK_TYPE_IWLAN:
                result = RadioTech.IWLAN;
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                result = RadioTech.LTE;
                break;
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                result = RadioTech.SCDMA;
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                result = RadioTech.UMTS;
                break;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                result = RadioTech.UNKNOWN;
                break;
            default:
                result = RadioTech.UNKNOWN;

        }
        return result;
    }

    @Override
    public Integer reverseMap(RadioTech value) {
        int result;
        switch (value) {
            case xRTT1:
                result = TelephonyManager.NETWORK_TYPE_1xRTT;
                break;
            case CDMA:
                result = TelephonyManager.NETWORK_TYPE_CDMA;
                break;
            case EDGE:
                result = TelephonyManager.NETWORK_TYPE_EDGE;
                break;
            case EHRPD:
                result = TelephonyManager.NETWORK_TYPE_EHRPD;
                break;
            case EVDO_0:
                result = TelephonyManager.NETWORK_TYPE_EVDO_0;
                break;
            case EVDO_A:
                result = TelephonyManager.NETWORK_TYPE_EVDO_A;
                break;
            case EVDO_B:
                result = TelephonyManager.NETWORK_TYPE_EVDO_B;
                break;
            case GPRS:
                result = TelephonyManager.NETWORK_TYPE_GPRS;
                break;
            case GSM:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    result = TelephonyManager.NETWORK_TYPE_GSM;
                }
                break;
            case HSDPA:
                result = TelephonyManager.NETWORK_TYPE_HSDPA;
                break;
            case HSPA:
                result = TelephonyManager.NETWORK_TYPE_HSPA;
                break;
            case HSPAP:
                result = TelephonyManager.NETWORK_TYPE_HSPAP;
                break;
            case HSUPA:
                result = TelephonyManager.NETWORK_TYPE_HSUPA;
                break;
            case IDEN:
                result = TelephonyManager.NETWORK_TYPE_IDEN;
                break;
            case IWLAN:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    result = TelephonyManager.NETWORK_TYPE_IWLAN;
                }
                break;
            case LTE:
                result = TelephonyManager.NETWORK_TYPE_LTE;
                break;
            case SCDMA:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    result = TelephonyManager.NETWORK_TYPE_TD_SCDMA;
                }
                break;
            case UMTS:
                result = TelephonyManager.NETWORK_TYPE_UMTS;
                break;
            case UNKNOWN:
                result = TelephonyManager.NETWORK_TYPE_UNKNOWN;
                break;
            default:
                result = TelephonyManager.NETWORK_TYPE_UNKNOWN;

        }
        return result;
    }
}
