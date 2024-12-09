# TorchServe Client for Java

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.tadayosi.torchserve/torchserve-client/badge.svg?style=flat)](https://repo1.maven.org/maven2/io/github/tadayosi/torchserve/torchserve-client/)
[![Test](https://github.com/tadayosi/torchserve-client-java/actions/workflows/test.yml/badge.svg)](https://github.com/tadayosi/torchserve-client-java/actions/workflows/test.yml)

TorchServe Client for Java (TSC4J) is a Java client library for [TorchServe](https://pytorch.org/serve/index.html). It supports the following [TorchServe REST API](https://pytorch.org/serve/rest_api.html):

- [Inference API](https://pytorch.org/serve/inference_api.html)
- [Management API](https://pytorch.org/serve/management_api.html)
- [Metrics API](https://pytorch.org/serve/metrics_api.html)

## Requirements

- Java 17+

## Install

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.tadayosi.torchserve</groupId>
    <artifactId>torchserve-client</artifactId>
    <version>0.4.0</version>
</dependency>
```

## Usage

### Inference

- Prediction:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  byte[] image = Files.readAllBytes(Path.of("0.png"));
  Object result = client.inference().predictions("mnist_v2", image);
  System.out.println(result);
  // => 0
  ```

- With the inference API endpoint other than <http://localhost:8080>:

  ```java
  TorchServeClient client = TorchServeClient.builder()
      .inferenceAddress("http://localhost:12345")
      .build();
  ```

- With token authorization:

  ```java
  TorchServeClient client = TorchServeClient.builder()
      .inferenceKey("<inference-key>")
      .build();
  ```

### Management

- Register a model:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  Response response = client.management().registerModel(
    "https://torchserve.pytorch.org/mar_files/mnist_v2.mar",
    RegisterModelOptions.empty());
  System.out.println(response.getStatus());
  // => "Model "mnist_v2" Version: 2.0 registered with 0 initial workers. Use scale workers API to add workers for the model."
  ```

- Scale workers for a model:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  Response response = client.management().setAutoScale(
    "mnist_v2",
    SetAutoScaleOptions.builder()
      .minWorker(1)
      .maxWorker(2)
      .build());
  System.out.println(response.getStatus());
  // => "Processing worker updates..."
  ```

- Describe a model:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  List<ModelDetail> model = client.management().describeModel("mnist_v2");
  System.out.println(model.get(0));
  // =>
  // ModelDetail {
  //     modelName: mnist_v2
  //     modelVersion: 2.0
  // ...
  ```

- Unregister a model:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  Response response = client.management().unregisterModel(
    "mnist_v2",
    UnregisterModelOptions.empty());
  System.out.println(response.getStatus());
  // => "Model "mnist_v2" unregistered"
  ```

- List models:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  ModelList models = client.management().listModels(10, null);
  System.out.println(models);
  // =>
  // ModelList {
  //     nextPageToken: null
  //     models: [Model {
  //     modelName: mnist_v2
  //     modelUrl: https://torchserve.pytorch.org/mar_files/mnist_v2.mar
  // },
  // ...
  ```

- Set default version for a model:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  Response response = client.management().setDefault("mnist_v2", "2.0");
  System.out.println(response.getStatus());
  // => "Default version successfully updated for model "mnist_v2" to "2.0""
  ```

- With the management API endpoint other than <http://localhost:8081>:

  ```java
  TorchServeClient client = TorchServeClient.builder()
      .managementAddress("http://localhost:12345")
      .build();
  ```

- With token authorization:

  ```java
  TorchServeClient client = TorchServeClient.builder()
      .managementKey("<management-key>")
      .build();
  ```

### Metrics

- Get metrics in Prometheus format:

  ```java
  TorchServeClient client = TorchServeClient.newInstance();

  String metrics = client.metrics().metrics();
  System.out.println(metrics);
  // =>
  // # HELP MemoryUsed Torchserve prometheus gauge metric with unit: Megabytes
  // # TYPE MemoryUsed gauge
  // MemoryUsed{Level="Host",Hostname="3a9b51d41fbf",} 2075.09765625
  // ...
  ```

- With the metrics API endpoint other than <http://localhost:8082>:

  ```java
  TorchServeClient client = TorchServeClient.builder()
      .metricsAddress("http://localhost:12345")
      .build();
  ```

## Configuration

### tsc4j.properties

```properties
inference.key = <inference-key>
inference.address = http://localhost:8080
# inference.address takes precedence over inference.port if it's defined
inference.port = 8080

management.key = <management-key>
management.address = http://localhost:8081
# management.address takes precedence over management.port if it's defined
management.port = 8081

metrics.address = http://localhost:8082
# metrics.address takes precedence over metrics.port if it's defined
metrics.port = 8082
```

### System properties

You can configure the TSC4J properties via system properties with prefix `tsc4j.`.

For instance, you can configure `inference.address` with the `tsc4j.inference.address` system property.

### Environment variables

You can also configure the TSC4J properties via environment variables with prefix `TSC4J_`.

For instance, you can configure `inference.address` with the `TSC4J_INFERENCE_ADDRESS` environment variable.

## Examples

See [examples](./examples/).

## Build

```console
mvn clean install
```
