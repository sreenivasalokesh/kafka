package com.learning.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class Consumer {

private static final Logger log = LoggerFactory.getLogger(Consumer.class);


    public static void main( String[] args )
    {
    	
    	String bootstrapServer = "172.28.5.75:9092";
    	String groupName = "my-second-group";
    	String topic = "my_second_topic";
    	
    	//create properties
    	Properties props = new Properties();
    	props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    	props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    	props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    	props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupName);
    	props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    	
    	
    	//create producer
    	KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    	
    	//subscribe to consumer
    	consumer.subscribe(Arrays.asList(topic));
    	
    	//poll messages
    	while(true) {
    		log.info("polling");
    		//poll all the records until no record to poll into the collection(records), 
    		//if no records left, wait for 1000 more ms (as mentioned in the Duration)
    		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
    		
    		for(ConsumerRecord<String, String> record: records) {
    			
    			log.info("###key: "+record.key());
    			log.info("###value: "+record.value());
    			log.info("###topic: "+ record.topic());
    			log.info("###partition: "+record.partition());
    			log.info("###offset: "+record.offset());
    			
    		}
    		
    		
    	}
    	
    	
    	
    	
    }
}
 	