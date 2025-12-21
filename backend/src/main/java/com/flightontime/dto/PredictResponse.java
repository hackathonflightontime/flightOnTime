package com.flightontime.dto;

public class PredictResponse {

    private boolean delay;
    private double probability;

    public PredictResponse(boolean delay, double probability) {
        this.delay = delay;
        this.probability = probability;
    }

    public boolean isDelay() {
        return delay;
    }

    public double getProbability() {
        return probability;
    }

    public void setDelayed(boolean b) {
    }

    public void setDelayProbability(double v) {
    }
}

