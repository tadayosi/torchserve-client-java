///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//DEPS io.github.tadayosi.torchserve:torchserve-client:0.4.0
//DEPS org.slf4j:slf4j-simple:1.7.36

import io.github.tadayosi.torchserve.client.TorchServeClient;
import io.github.tadayosi.torchserve.client.model.ApiException;

public class token_auth {

    /**
     * You can find the auth token for the inference API by looking at the
     * `key_file.json` in the current directory of the TorchServe server.
     */
    private static String INFERENCE_AUTH_TOKEN = "<inference-key>";

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.builder()
                    .inferenceKey(INFERENCE_AUTH_TOKEN)
                    .build();
            var response = client.inference().ping();
            System.out.println(response);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
