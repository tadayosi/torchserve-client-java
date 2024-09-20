package com.github.tadayosi.torchserve.client;

import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

class TorchServeTestSupport {

    private static final String IMAGE_NAME = "aarch64".equals(System.getProperty("os.arch")) ? "tadayosi/torchserve" : "pytorch/torchserve";

    @Container
    static GenericContainer<?> torchServe = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME))
        .withExposedPorts(8080, 8081, 8082)
        .withCopyFileToContainer(MountableFile.forClasspathResource("torchserve/config.properties"), "/home/model-server/config.properties")
        .withCopyFileToContainer(MountableFile.forClasspathResource("models/squeezenet1_1.mar"), "/home/model-server/model-store/squeezenet1_1.mar")
        .waitingFor(Wait.forListeningPorts(8080, 8081, 8082))
        .withCommand("torchserve --ncs --disable-token-auth --enable-model-api --model-store /home/model-server/model-store --models squeezenet1_1.mar");

    protected TorchServeClient client;

    @BeforeEach
    void setUp() {
        client = TorchServeClient.builder()
            .inferencePort(torchServe.getMappedPort(8080))
            .managementPort(torchServe.getMappedPort(8081))
            .metricsPort(torchServe.getMappedPort(8082))
            .build();
    }
}
