///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import java.nio.file.Files;
import java.nio.file.Path;

import io.github.tadayosi.torchserve.client.TorchServeClient;
import io.github.tadayosi.torchserve.client.model.ApiException;

public class mnist {

    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        var zero = Files.readAllBytes(Path.of("src/test/resources/data/0.png"));
        var one = Files.readAllBytes(Path.of("src/test/resources/data/1.png"));
        try {
            var client = TorchServeClient.newInstance();
            var result0 = client.inference().predictions(MNIST_MODEL, zero);
            System.out.println("Answer> " + result0);
            var result1 = client.inference().predictions(MNIST_MODEL, one);
            System.out.println("Answer> " + result1);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
