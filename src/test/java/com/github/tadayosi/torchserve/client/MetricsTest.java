package com.github.tadayosi.torchserve.client;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
class MetricsTest extends TorchServeTestSupport {

    @Test
    void testMetrics() throws Exception {
        var response = client.metrics().metrics();
        assertNotNull(response);
    }
}
