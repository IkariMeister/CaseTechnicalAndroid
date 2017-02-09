package com.jcgarcia.casetechtest.module;

/**
 * Created by jcgarcia on 8/2/17.
 */

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
