package io.github.tadayosi.torchserve.client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
class InferenceTest extends io.github.tadayosi.torchserve.client.TorchServeTestSupport {

    private static final String DEFAULT_MODEL = "squeezenet1_1";
    private static final String DEFAULT_MODEL_VERSION = "1.0";
    private static final String TEST_DATA = "src/test/resources/data/kitten.jpg";

    @Test
    void testApiDescription() throws Exception {
        var response = client.inference().apiDescription();
        assertNotNull(response);
    }

    @Test
    void testPing() throws Exception {
        var response = client.inference().ping();
        assertEquals("Healthy", response.getStatus());
    }

    @Test
    void testPredictions() throws Exception {
        var body = Files.readAllBytes(Path.of(TEST_DATA));
        var response = client.inference().predictions(DEFAULT_MODEL, body);
        assertInstanceOf(Map.class, response);
    }

    @Test
    void testPredictions_version() throws Exception {
        var body = Files.readAllBytes(Path.of(TEST_DATA));
        var response = client.inference().predictions(DEFAULT_MODEL, DEFAULT_MODEL_VERSION, body);
        assertInstanceOf(Map.class, response);
    }

    @Test
    void testExplanations() {
        assertThrows(UnsupportedOperationException.class, () -> client.inference().explanations(DEFAULT_MODEL));
    }

}
