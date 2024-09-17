package com.github.tadayosi.torchserve.client;

import com.github.tadayosi.torchserve.client.impl.DefaultMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class MetricsTest extends TorchServeTestSupport {

    private Metrics metrics;

    @BeforeEach
    public void setUp() {
        metrics = new DefaultMetrics(torchServe.getMappedPort(8082));
    }

    @Test
    public void testMetrics() throws Exception {
        var response = metrics.metrics();
        assertNotNull(response);
    }
}
