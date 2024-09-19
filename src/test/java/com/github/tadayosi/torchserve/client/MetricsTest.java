package com.github.tadayosi.torchserve.client;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class MetricsTest extends TorchServeTestSupport {

    @Test
    public void testMetrics() throws Exception {
        var response = client.metrics().metrics();
        assertNotNull(response);
    }
}
