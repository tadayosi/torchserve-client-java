///usr/bin/env jbang "$0" "$@" ; exit $?

//JAVA 17+
//REPOS mavencentral,jitpack=https://jitpack.io
//DEPS com.github.tadayosi:torchserve-client-java:main-SNAPSHOT
//DEPS org.slf4j:slf4j-simple:1.7.36

import com.github.tadayosi.torchserve.client.TorchServeClient;
import com.github.tadayosi.torchserve.client.model.ApiException;

public class metrics {

    public static void main(String... args) throws Exception {
        try {
            var client = TorchServeClient.newInstance();
            var metrics = client.metrics().metrics();
            System.out.println(metrics);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
