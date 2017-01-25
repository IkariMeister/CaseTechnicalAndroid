package com.jcgarcia.casetechtest.datasource.location;

/**
 * Created by jcgarcia on 25/1/17.
 */

public interface LocationReader<V> {
    String read(V location);
}
