package com.jcgarcia.casetechtest.datasource;

import com.jcgarcia.casetechtest.domain.entity.RadioInfo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jcgarcia on 24/1/17.
 */
public class FakeRadioDataSourceTest {
    @Test
    public void getCurrent_Ok() throws Exception {
        RadioInfo expected = new RadioInfo();

        RadioInfo actual = new FakeRadioDataSource().getCurrent();

        assertEquals(expected,actual);
    }

}