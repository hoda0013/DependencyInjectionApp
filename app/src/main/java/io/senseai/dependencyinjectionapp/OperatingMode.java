package io.senseai.dependencyinjectionapp;

/**
 * Created by bigern on 12/26/15.
 */
public class OperatingMode {
    Mode mode = Mode.OFF;
    long timestamp;
    double rand;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getRand() {
        return rand;
    }

    public void setRand(double rand) {
        this.rand = rand;
    }
}
