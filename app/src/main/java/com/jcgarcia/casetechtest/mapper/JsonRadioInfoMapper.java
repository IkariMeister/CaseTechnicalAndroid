package com.jcgarcia.casetechtest.mapper;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;
import com.owlike.genson.Genson;

import javax.inject.Inject;

/**
 * Created by jcgarcia on 25/1/17.
 */

public class JsonRadioInfoMapper extends Mapper<RadioInfo,String> {

    @Inject
    private Genson gs;

    @Override
    public String map(RadioInfo value) {
        return gs.serialize(value);
    }

    @Override
    public RadioInfo reverseMap(String value) {
        return gs.deserialize(value,RadioInfo.class);
    }
}
