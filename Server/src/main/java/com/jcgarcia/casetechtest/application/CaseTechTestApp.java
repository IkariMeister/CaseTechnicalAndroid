package com.jcgarcia.casetechtest.application;

import android.app.Application;

import com.jcgarcia.casetechtest.module.AndroidModules;
import com.jcgarcia.casetechtest.module.MapperModule;
import com.jcgarcia.casetechtest.module.TelephonyModule;
import com.jcgarcia.casetechtest.module.UseCaseModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by jcgarcia on 24/1/17.
 */

public class CaseTechTestApp extends Application {

    private ObjectGraph graph;

    @Override public void onCreate() {
        super.onCreate();
        initGraph();
        injectApplication();
    }

    public final void inject(Object object) {
        graph.inject(object);
    }

    public ObjectGraph plusGraph(List<Object> activityScopeModules) {
        if (activityScopeModules == null) {
            throw new IllegalArgumentException(
                    "You can't extend the application graph with a null list of modules");
        }
        return graph.plus(activityScopeModules.toArray());
    }

    protected List<Object> getApplicationModules() {
        return new ArrayList<>();
    }

    private void initGraph() {
        List<Object> appModules = getModules();
        List<Object> modules = new ArrayList<>(appModules);
        List<Object> applicationModules = getApplicationModules();

        if (applicationModules != null) {
            modules.addAll(applicationModules);
        }
        graph = ObjectGraph.create(modules.toArray());
    }

    private void injectApplication() {
            graph.inject(this);
            graph.injectStatics();

    }

    private List<Object> getModules() {
        return Arrays.asList(new AndroidModules(this), new MapperModule(),
                new UseCaseModule(), new TelephonyModule());
    }



}
