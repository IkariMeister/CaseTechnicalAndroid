package com.jcgarcia.casetechtest.mapper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jcgarcia on 24/1/17.
 */

public abstract class Mapper<V,T> {

    public abstract T map(V value);
    public abstract V reverseMap(T value);

    public Collection<T> map(Collection<V> values){
        Collection<T> result = new ArrayList<T>(values.size());
        for(V value:values){
            result.add(map(value));
        }
        return result;
    }

    public Collection<V> reverseMap(Collection<T> values){
        Collection<V> result = new ArrayList<V>(values.size());
        for(T value:values){
            result.add(reverseMap(value));
        }
        return result;
    }
}
