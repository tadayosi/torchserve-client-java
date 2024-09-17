package com.github.tadayosi.torchserve.client;

import java.util.Map;

import com.github.tadayosi.torchserve.client.impl.DefaultManagement;
import com.github.tadayosi.torchserve.client.management.invoker.ApiException;
import com.github.tadayosi.torchserve.client.management.model.InlineResponse200;
import com.github.tadayosi.torchserve.client.management.model.InlineResponse2001;
import com.github.tadayosi.torchserve.client.management.model.InlineResponse2002;
import com.github.tadayosi.torchserve.client.management.model.InlineResponse2003;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class ManagementTest extends TorchServeTestSupport {

    private static final String DEFAULT_MODEL = "squeezenet1_1";
    private static final String DEFAULT_MODEL_VERSION = "1.0";
    private static final String ADDED_MODEL = "mnist_v2";
    private static final String ADDED_MODEL_VERSION = "2.0";

    private Management management;

    @BeforeEach
    public void setUp() throws Exception {
        management = new DefaultManagement(torchServe.getMappedPort(8081));
    }

    @Test
    public void testRegisterModel() throws Exception {
        var url = "https://torchserve.pytorch.org/mar_files/mnist_v2.mar";
        try {
            var response = management.registerModel(url, RegisterModelOptions.empty());
            assertTrue(((InlineResponse2002) response).getStatus().contains("registered"));
        } catch (ApiException e) {
            e.printStackTrace();
            fail(e.getResponseBody());
        }
    }

    @Nested
    class AfterRegisteringModel {

        @BeforeEach
        public void registerModel() throws Exception {
            var url = "https://torchserve.pytorch.org/mar_files/mnist_v2.mar";
            try {
                management.registerModel(url, RegisterModelOptions.empty());
            } catch (ApiException e) {
                // Ignore if the model is already registered
            }
        }

        @Test
        public void testUnregisterModel() throws Exception {
            var response = management.unregisterModel(ADDED_MODEL, UnregisterModelOptions.empty());
            assertTrue(((InlineResponse2002) response).getStatus().contains("unregistered"));
        }

        @Test
        public void testUnregisterModel_version() throws Exception {
            var response = management.unregisterModel(ADDED_MODEL, ADDED_MODEL_VERSION, UnregisterModelOptions.empty());
            assertTrue(((InlineResponse2002) response).getStatus().contains("unregistered"));
        }

        @Nested
        class BeforeUnregisteringModel {

            @AfterEach
            public void unregisterModel() throws Exception {
                management.unregisterModel(ADDED_MODEL, UnregisterModelOptions.empty());
            }

            @Test
            public void testSetAutoScale() throws Exception {
                var response = management.setAutoScale(ADDED_MODEL,
                    SetAutoScaleOptions.builder()
                        .minWorker(1)
                        .build());
                assertTrue(((InlineResponse2002) response).getStatus().contains("Processing worker updates"));
            }

            @Test
            public void testSetAutoScale_version() throws Exception {
                var response = management.setAutoScale(ADDED_MODEL, ADDED_MODEL_VERSION,
                    SetAutoScaleOptions.builder()
                        .minWorker(1)
                        .build());
                assertTrue(((InlineResponse2002) response).getStatus().contains("Processing worker updates"));
            }
        }
    }

    @Test
    public void testDescribeModel() throws Exception {
        var response = management.describeModel(DEFAULT_MODEL);
        assertEquals(1, response.size());
        assertEquals("squeezenet1_1", ((InlineResponse2003) response.get(0)).getModelName());
    }

    @Test
    public void testDescribeModel_version() throws Exception {
        var response = management.describeModel(DEFAULT_MODEL, DEFAULT_MODEL_VERSION);
        assertEquals(1, response.size());
        assertEquals("squeezenet1_1", ((InlineResponse2003) response.get(0)).getModelName());
        assertEquals("1.0", ((InlineResponse2003) response.get(0)).getModelVersion());
    }

    @Test
    public void testListModels() throws Exception {
        int limit = 10;
        String nextPageToken = null;
        var response = management.listModels(limit, nextPageToken);
        var models = ((InlineResponse2001) response).getModels();
        assertFalse(models.isEmpty());
        assertEquals(DEFAULT_MODEL, ((Map<?, ?>) models.get(0)).get("modelName"));
    }

    @Test
    public void testSetDefault() throws Exception {
        var response = management.setDefault(DEFAULT_MODEL, DEFAULT_MODEL_VERSION);
        assertTrue(((InlineResponse2002) response).getStatus().contains("Default vesion succsesfully updated"));
    }

    @Test
    public void testApiDescription() throws Exception {
        var response = management.apiDescription();
        assertEquals("TorchServe APIs", ((Map<?, ?>) ((InlineResponse200) response).getInfo()).get("title"));
    }

    @Test
    public void testToken() throws Exception {
        assertThrows(UnsupportedOperationException.class, () -> management.token("management"));
    }

}
