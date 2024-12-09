///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//DEPS io.github.tadayosi.torchserve:torchserve-client:0.4.0
//DEPS org.slf4j:slf4j-simple:1.7.36

import io.github.tadayosi.torchserve.client.TorchServeClient;
import io.github.tadayosi.torchserve.client.model.ApiException;

public class set_default {

    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var response = client.management().setDefault(MNIST_MODEL, "2.0");
            System.out.println("Status> " + response.getStatus());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
