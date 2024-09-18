package com.github.tadayosi.torchserve.client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.github.tadayosi.torchserve.client.impl.DefaultInference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
public class InferenceTest extends TorchServeTestSupport {

    private static final String DEFAULT_MODEL = "squeezenet1_1";
    private static final String DEFAULT_MODEL_VERSION = "1.0";
    private static final String TEST_DATA = "src/test/resources/data/kitten.jpg";

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
        var response = inference.ping();
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
        var body = Files.readAllBytes(Path.of(TEST_DATA));
        var response = inference.predictions(DEFAULT_MODEL, DEFAULT_MODEL_VERSION, body);
        assertInstanceOf(Map.class, response);
    }

    @Test
    public void testExplanations() {
        assertThrows(UnsupportedOperationException.class, () -> inference.explanations(DEFAULT_MODEL));
    }

}
