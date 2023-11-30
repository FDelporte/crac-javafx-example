# CRaC JavaFX Example Application

Based on [github.com/CRaC/example-jetty](https://github.com/CRaC/example-jetty) to experiment with the use
of [CRaC](https://docs.azul.com/core/crac/crac-introduction).

## Goal of the Application

This demo application aims to provide a basic JavaFX application to test its compatibility with CRaC.

## FAT JAR

Created following this
approach: https://medium.com/@kennydop/how-to-create-an-executable-jar-file-for-javafx-using-maven-f8a0039de1fa

## Testing on Raspberry Pi

### OS and Java

As described on the blog
post [Running a CRaC Java application on Raspberry Pi - UPDATE](https://webtechie.be/post/2023-10-16-crac-on-raspberry-pi-update/).

* Raspberry Pi OS, 64-bit, Bookworm edition, released on October 11, 2023.
* Azul Zulu Builds of OpenJDK, version 21 with CRaC:

```bash
$ sdk install java 21.crac-zulu
```

### Get the Project

```bash
$ git clone https://github.com/FDelporte/crac-javafx-example.git
$ cd crac-javafx-example
```

### Build and Initial Run

#### First Terminal

```bash
$ mvn package
$ java -XX:CRaCCheckpointTo=cr -jar target/crac-javafx-example.jar
```

#### Second Terminal

```bash
$ jcmd target/crac-javafx-example.jar JDK.checkpoint
```

In the first terminal, you can see what's happening during the checkpoint creation:

```text

```

### Start From Snapshot

```bash
$ java -XX:CRaCRestoreFrom=cr

```