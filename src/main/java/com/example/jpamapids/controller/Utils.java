package com.example.jpamapids.controller;

import io.micrometer.core.instrument.MeterRegistry;

import java.util.List;

public final class Utils {

    private static List<String> collectedMetrics = List.of("hikaricp.connections.active", "hikaricp.connections.idle");

    public static void printHikariConnectionMetric(MeterRegistry meterRegistry) {
        try {
            Thread.sleep(1_100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        meterRegistry.forEachMeter(meter -> {
            if (collectedMetrics.contains(meter.getId().getName())) {
                System.out.println(meter.getId().getName());
                meter.measure().forEach(measurement -> {
                    System.out.println(measurement.getValue());
                });
            }
        });
    }
}
