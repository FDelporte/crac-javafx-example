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
$ sdk install java 21.0.1.crac-zulu
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

**First attempt 20231201**

Output in the program terminal:

```text
java -XX:CRaCCheckpointTo=cr -jar target/crac-javafx-example.jarar
Dec 01, 2023 7:15:15 AM com.sun.javafx.application.PlatformImpl startup
WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @373564dd'
WARNING: sun.reflect.Reflection.getCallerClass is not supported. This will impact performance.
01/12/2023 07:15:18.544 | MainApplication                     | start                | INFO     | Starting the stage
01/12/2023 07:15:20.143 | MainApplication                     | start                | INFO     | Stage was started
01/12/2023 07:15:20.146 | MainApplication                     | start                | INFO     | Scheduled a task to update the data
01/12/2023 07:15:21.147 | MainWindow                          | setRandomValue       | INFO     | Setting gauge to 207.0
01/12/2023 07:15:22.146 | MainWindow                          | setRandomValue       | INFO     | Setting gauge to 178.0
...
01/12/2023 07:15:51.146 | MainWindow                          | setRandomValue       | INFO     | Setting gauge to 402.0
Dec 01, 2023 7:15:51 AM jdk.internal.crac.LoggerContainer info
INFO: Starting checkpoint
01/12/2023 07:15:51.508 | MainWindow                          | beforeCheckpoint     | INFO     | beforeCheckpoint was called
Dec 01, 2023 7:15:51 AM jdk.internal.crac.LoggerContainer info
INFO: /home/crac/crac-javafx-example/target/crac-javafx-example.jar is recorded as always available on restore
01/12/2023 07:15:51.852 | MainWindow                          | afterRestore         | INFO     | afterRestore was called
...
```

Error message in the terminal with `jcmd`, nothing gets created in the `cr` directory:

```text
$ jcmd target/crac-javafx-example.jar JDK.checkpoint
2263:
An exception during a checkpoint operation:
jdk.internal.crac.mirror.CheckpointException
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenSocketException: FD fd=7 type=socket path=socket:[19379]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:115)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenFileException: FD fd=8 type=character path=/dev/dri/card1
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:114)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenFileException: FD fd=9 type=character path=/dev/dri/card1
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:114)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenFileException: FD fd=10 type=character path=/dev/dri/renderD128
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:114)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenSocketException: FD fd=11 type=socket path=socket:[21172]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:115)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenResourceException: FD fd=12 type=unknown path=anon_inode:[eventfd]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:117)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenResourceException: FD fd=13 type=unknown path=anon_inode:[eventfd]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:117)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenResourceException: FD fd=14 type=unknown path=anon_inode:[eventfd]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:117)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenSocketException: FD fd=15 type=socket path=socket:[20304]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:115)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
	Suppressed: jdk.internal.crac.mirror.impl.CheckpointOpenResourceException: FD fd=16 type=unknown path=anon_inode:[eventfd]
		at java.base/jdk.internal.crac.mirror.Core.translateJVMExceptions(Core.java:117)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore1(Core.java:188)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestore(Core.java:286)
		at java.base/jdk.internal.crac.mirror.Core.checkpointRestoreInternal(Core.java:299)
```

### Start From Snapshot

```bash
$ java -XX:CRaCRestoreFrom=cr

```