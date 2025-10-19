package edu.ensias.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class EventProducer {
    public static void main(String[] args) throws Exception {

        // Vérifier qu’un topic est fourni
        if(args.length == 0){
            System.out.println("Entrer le nom du topic");
            return;
        }

        String topicName = args[0].toString(); // lire le topicName fourni comme param

        // Configurations du producteur Kafka
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");   // broker Kafka
        props.put("acks", "all");                           // acquittement
        props.put("retries", 0);                            // nombre de tentatives
        props.put("batch.size", 16384);                     // taille du batch
        props.put("buffer.memory", 33554432);              // mémoire tampon
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Envoyer 10 messages simples
        for(int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>(topicName, Integer.toString(i), Integer.toString(i)));

        System.out.println("Messages envoyés avec succès");
        producer.close();
    }
}
