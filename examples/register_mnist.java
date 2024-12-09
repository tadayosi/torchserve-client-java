///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS io.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import io.github.tadayosi.torchserve.client.TorchServeClient;
import io.github.tadayosi.torchserve.client.model.ApiException;
import io.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import io.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;

public class register_mnist {

    private static String MNIST_URL = "https://torchserve.pytorch.org/mar_files/mnist_v2.mar";
    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var response = client.management().registerModel(MNIST_URL, RegisterModelOptions.empty());
            System.out.println("registerModel> " + response.getStatus());
            response = client.management().setAutoScale(MNIST_MODEL, SetAutoScaleOptions.builder()
                    .minWorker(1)
                    .maxWorker(1)
                    .build());
            System.out.println("setAutoScale> " + response.getStatus());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
