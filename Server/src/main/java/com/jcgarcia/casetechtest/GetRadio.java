package com.jcgarcia.casetechtest;

import com.jcgarcia.casetechtest.datasource.RadioDataSourceFactory;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class GetRadio {

    /**
     * This is a fast aproach to use cases this should be asyncronous and return invokes
     * successCallback and catch invokes error callback
     * In this case boolean value represents RegisterOn/RegisterOff messages
     */


    @Inject
    RadioDataSourceFactory factory;

    public void doGetRadio() {
        try {
            //success callback here with data returned
            factory.getDataSource().getCurrent();
        } catch (Exception e) {
            //error callback here with error treatment
        }
    }
}
