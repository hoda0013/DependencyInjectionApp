package io.senseai.dependencyinjectionapp;

/**
 * Created by bigern on 12/26/15.
 */
public interface DatabaseAccess {
    OperatingMode getMode();
    void setMode(OperatingMode mode);
}
