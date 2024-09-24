# TorchServe Client for Java

[![Release](https://jitpack.io/v/tadayosi/torchserve-client-java.svg)](<https://jitpack.io/#tadayosi/torchserve-client-java>)
[![Test](https://github.com/tadayosi/torchserve-client-java/actions/workflows/test.yml/badge.svg)](https://github.com/tadayosi/torchserve-client-java/actions/workflows/test.yml)

TorchServe Client for Java (TSC4J) is a Java client library for [TorchServe](https://pytorch.org/serve/index.html). It supports the following [TorchServe REST API](https://pytorch.org/serve/rest_api.html):

- [Inference API](https://pytorch.org/serve/inference_api.html)
- [Management API](https://pytorch.org/serve/management_api.html)
- [Metrics API](https://pytorch.org/serve/metrics_api.html)

## Install

1. Add the [JitPack](https://jitpack.io) repository to your `pom.xml`:

    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```

2. Add the dependency:

    ```xml
    <dependency>
        <groupId>com.github.tadayosi</groupId>
        <artifactId>torchserve-client-java</artifactId>
        <version>main-SNAPSHOT</version>
    </dependency>
    ```

## Usage

See [examples](./examples/).

## Build

```console
mvn clean install
```
