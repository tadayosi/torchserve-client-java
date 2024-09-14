package com.github.tadayosi.torchserve.client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.github.tadayosi.torchserve.client.impl.DefaultInference;
import com.github.tadayosi.torchserve.client.inference.model.InlineResponse2001;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
public class InferenceTest {

    private static final String DEFAULT_MODEL = "squeezenet1_1";
    private static final String TEST_DATA = "src/test/resources/data/kitten.jpg";

    @Container
    public static GenericContainer<?> torchServe = new GenericContainer<>(DockerImageName.parse("tadayosi/torchserve"))
        .withExposedPorts(8080)
        .withCopyFileToContainer(MountableFile.forClasspathResource("config.properties"), "/home/model-server/config.properties")
        .withCopyFileToContainer(MountableFile.forClasspathResource("models/squeezenet1_1.mar"), "/tmp/models/squeezenet1_1.mar")
        .waitingFor(Wait.forListeningPorts(8080))
        .withCommand("torchserve --ncs --disable-token-auth --enable-model-api --model-store /tmp/models --models squeezenet1_1.mar");

    private Inference inference;

    @BeforeEach
    public void setUp() {
        inference = new DefaultInference(torchServe.getMappedPort(8080));
    }

    @Test
    public void testApiDescription() throws Exception {
        var response = inference.apiDescription();
        assertNotNull(response);
    }

    @Test
    public void testPing() throws Exception {
        var response = (InlineResponse2001) inference.ping();
        assertEquals("Healthy", response.getStatus());
    }

    @Test
    public void testPredictions() throws Exception {
        var body = Files.readAllBytes(Path.of(TEST_DATA));
        var response = inference.predictions(DEFAULT_MODEL, body);
        assertInstanceOf(Map.class, response);
    }

    @Test
    public void testPredictions_version() throws Exception {
        var modelVersion = "1.0";
        var body = Files.readAllBytes(Path.of(TEST_DATA));
        var response = inference.predictions(DEFAULT_MODEL, modelVersion, body);
        assertInstanceOf(Map.class, response);
    }

    @Test
    public void testExplanations() {
        assertThrows(UnsupportedOperationException.class, () -> inference.explanations(DEFAULT_MODEL));
    }

}
