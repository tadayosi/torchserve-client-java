///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS com.github.tadayosi.torchserve:torchserve-client:0.1-SNAPSHOT

import java.nio.file.Files;
import java.nio.file.Path;

import com.github.tadayosi.torchserve.client.impl.DefaultInference;
import com.github.tadayosi.torchserve.client.model.ApiException;

public class mnist {

    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        var zero = Files.readAllBytes(Path.of("src/test/resources/data/0.png"));
        var one = Files.readAllBytes(Path.of("src/test/resources/data/1.png"));
        try {
            var inference = new DefaultInference();
            var result0 = inference.predictions(MNIST_MODEL, zero);
            System.out.println("Answer> " + result0);
            var result1 = inference.predictions(MNIST_MODEL, one);
            System.out.println("Answer> " + result1);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
