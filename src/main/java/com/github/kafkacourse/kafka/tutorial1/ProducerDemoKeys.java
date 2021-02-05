package com.github.kafkacourse.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class.getName());

        String bootstrapServers = "localhost:9092";

        // Properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for(int i = 0; i < 10; i++) {
            String topic = "first_topic";
            String value = "Hello world " + i;
            String key = "id_" + i;

            // Producer record
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

            // Log the key
            logger.info("Key: " + key);

            // Send data
            producer.send(record, (recordMetadata, e) -> {
                if(e == null){
                    logger.info("Received new metadata. \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset:" + recordMetadata.offset() + "\n" +
                            "Timestamp:" + recordMetadata.timestamp());
                } else {
                    logger.error("Error while producing", e);
                }
            }).get(); // block send() to make it synchronous - don't use this in production
        }

        producer.flush();
        producer.close();
    }
}
