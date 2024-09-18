///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS com.github.tadayosi.torchserve:torchserve-client:0.1-SNAPSHOT

import com.github.tadayosi.torchserve.client.impl.DefaultManagement;
import com.github.tadayosi.torchserve.client.management.invoker.ApiException;
import com.github.tadayosi.torchserve.client.model.RegisterModelOptions;
import com.github.tadayosi.torchserve.client.model.SetAutoScaleOptions;

public class register_mnist {

    private static String MNIST_URL = "https://torchserve.pytorch.org/mar_files/mnist_v2.mar";
    private static String MNIST_MODEL = "mnist_v2";

    public static void main(String... args) throws Exception {
        try {
            var management = new DefaultManagement();
            var response = management.registerModel(MNIST_URL, RegisterModelOptions.empty());
            System.out.println("registerModel> " + response.getStatus());
            response = management.setAutoScale(MNIST_MODEL, SetAutoScaleOptions.builder()
                    .minWorker(1)
                    .maxWorker(1)
                    .build());
            System.out.println("setAutoScale> " + response.getStatus());
        } catch (ApiException e) {
            System.err.println(e.getResponseBody());
        }
    }
}
