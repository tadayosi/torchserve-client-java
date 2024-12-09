///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS io.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import io.github.tadayosi.torchserve.client.TorchServeClient;
import io.github.tadayosi.torchserve.client.model.ApiException;

public class describe_model {

    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var models = client.management().describeModel(MNIST_MODEL);
            System.out.println("Model> " + models.get(0));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
