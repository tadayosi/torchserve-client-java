package com.github.tadayosi.torchserve.client;

import com.github.tadayosi.torchserve.client.model.ApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigurationTest {

    @Test
    void testLoad() {
        var config = Configuration.load();
        assertNotNull(config);
    }

    @Test
    void testSystemProperties() {
        System.setProperty("tsc4j.inference.key", "aaaaa");
        System.setProperty("tsc4j.inference.address", "https://test.com:8180");
        System.setProperty("tsc4j.inference.port", "8180");
        System.setProperty("tsc4j.management.key", "bbbbb");
        System.setProperty("tsc4j.management.address", "https://test.com:8181");
        System.setProperty("tsc4j.management.port", "8181");
        System.setProperty("tsc4j.metrics.address", "https://test.com:8182");
        System.setProperty("tsc4j.metrics.port", "8182");

        var config = Configuration.load();

        assertEquals("aaaaa", config.getInferenceKey().get());
        assertEquals("https://test.com:8180", config.getInferenceAddress().get());
        assertEquals(8180, config.getInferencePort().get());
        assertEquals("bbbbb", config.getManagementKey().get());
        assertEquals("https://test.com:8181", config.getManagementAddress().get());
        assertEquals(8181, config.getManagementPort().get());
        assertEquals("https://test.com:8182", config.getMetricsAddress().get());
        assertEquals(8182, config.getMetricsPort().get());
    }
}
