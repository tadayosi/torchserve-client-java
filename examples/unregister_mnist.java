///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import com.github.tadayosi.torchserve.client.TorchServeClient;
import com.github.tadayosi.torchserve.client.model.ApiException;
import com.github.tadayosi.torchserve.client.model.UnregisterModelOptions;

public class unregister_mnist {

    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var response = client.management().unregisterModel(MNIST_MODEL, UnregisterModelOptions.empty());
            System.out.println("Status> " + response.getStatus());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
