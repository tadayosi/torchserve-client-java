///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import com.github.tadayosi.torchserve.client.TorchServeClient;
import com.github.tadayosi.torchserve.client.model.ApiException;

public class list_models {

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var models = client.management().listModels(10, null);
            System.out.println("Models> " + models);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
