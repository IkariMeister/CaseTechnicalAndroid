package com.jcgarcia.casetechtest;

import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class Register {

    @Inject
    RadioDataSourceFactory factory;


    /**
     * This is a fast aproach to use cases this should be asyncronous and return invokes
     * successCallback and catch invokes error callback
     * In this case boolean value represents RegisterOn/RegisterOff messages
     * <p>
     * My aproach is if the service goes to unregisted mode instead of reading data from
     * TelephonyManagerDataSource, it's read them from a Fake, proving that the work could be keep on
     * by implementing a new unregisted DataSource
     */


    public void doRegister(boolean on) {
        try {
            if (on) {
                factory.setType(RadioDataSourceFactory.RADIO_ON);
            } else {
                factory.setType(RadioDataSourceFactory.RADIO_OFF);
            }
            //success callback here with data returned
        } catch (Exception e) {

            //error callback here with error treatment
        }
    }

}
