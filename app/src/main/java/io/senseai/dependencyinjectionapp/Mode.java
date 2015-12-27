package io.senseai.dependencyinjectionapp;

/**
 * Created by bigern on 12/26/15.
 */
public enum Mode {
    OFF,
    CONTINUOUS,
    TIMER;

    public static Mode fromName(String name) {
        return valueOf(name);
    }
}
