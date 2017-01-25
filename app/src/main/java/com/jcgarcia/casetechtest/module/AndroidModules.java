package com.jcgarcia.casetechtest.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcgarcia on 24/1/17.
 */


@Module(library = true) public class AndroidModules {

    private final Context context;

    public AndroidModules(Application application) {
        validateApplication(application);
        this.context = application;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    private void validateApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("The Application passed in construction can't be null.");
        }
    }
}
