# Kafka Producer & Consumer

Basic fundamentals of Kafka.

- Topics
- Consumer
- Producer

## Requirements

- [Java JDK 8](https://www.oracle.com/ie/java/technologies/javase/javase-jdk8-downloads.html)
- [Apache Kafka](https://kafka.apache.org/downloads)
- [Intellij Community](https://www.jetbrains.com/idea/download/)

## Install
- [Step-by-step](https://kafka.apache.org/quickstart)

## Start the servers

```sh
# start the zookeeper (Windows)
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# start the server
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Running on Intellij
After starting the servers, you can run consumer and producer

1. You can start multiple consumers and see that each consumer is reading from different partitions.

## Reference
- [Documentation](https://kafka.apache.org/)
