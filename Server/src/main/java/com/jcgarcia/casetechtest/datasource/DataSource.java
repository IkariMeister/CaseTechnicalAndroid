package com.jcgarcia.casetechtest.datasource;

/**
 * Created by ikari on 24/1/17.
 */

public interface DataSource<V> {
    V getCurrent() throws Exception;
}
