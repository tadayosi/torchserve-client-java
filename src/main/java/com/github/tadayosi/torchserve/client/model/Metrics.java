package com.github.tadayosi.torchserve.client.model;

import com.github.tadayosi.torchserve.client.management.model.ModelsmodelNameMetrics;

public class Metrics {

    public Metrics() {
    }

    public static Metrics from(ModelsmodelNameMetrics src) {
        Metrics metrics = new Metrics();
        return metrics;
    }
}
