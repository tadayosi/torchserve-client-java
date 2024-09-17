package com.github.tadayosi.torchserve.client;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class TorchServeTestSupport {

    private static final String IMAGE_NAME = "aarch64".equals(System.getProperty("os.arch")) ? "tadayosi/torchserve" : "pytorch/torchserve";

    @Container
    public static GenericContainer<?> torchServe = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME))
        .withExposedPorts(8080, 8081, 8082)
        .withCopyFileToContainer(MountableFile.forClasspathResource("config.properties"), "/home/model-server/config.properties")
        .withCopyFileToContainer(MountableFile.forClasspathResource("models/squeezenet1_1.mar"), "/home/model-server/model-store/squeezenet1_1.mar")
        .waitingFor(Wait.forListeningPorts(8080))
        .withCommand("torchserve --ncs --disable-token-auth --enable-model-api --model-store /home/model-server/model-store --models squeezenet1_1.mar");
}
