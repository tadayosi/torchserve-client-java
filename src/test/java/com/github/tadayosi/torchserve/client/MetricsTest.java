package com.github.tadayosi.torchserve.client;

import com.github.tadayosi.torchserve.client.impl.DefaultMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class MetricsTest {

    @Container
    public static GenericContainer<?> torchServe = new GenericContainer<>(DockerImageName.parse("tadayosi/torchserve"))
        .withExposedPorts(8082)
        .withCopyFileToContainer(MountableFile.forClasspathResource("config.properties"), "/home/model-server/config.properties")
        .withCopyFileToContainer(MountableFile.forClasspathResource("models/squeezenet1_1.mar"), "/tmp/models/squeezenet1_1.mar")
        .waitingFor(Wait.forListeningPorts(8082))
        .withCommand("torchserve --ncs --disable-token-auth --enable-model-api --model-store /tmp/models --models squeezenet1_1.mar");

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
