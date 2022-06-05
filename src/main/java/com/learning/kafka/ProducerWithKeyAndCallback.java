package com.learning.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class ProducerWithKeyAndCallback {

private static final Logger log = LoggerFactory.getLogger(ProducerWithKeyAndCallback.class);


    public static void main( String[] args )
    {
    	log.info( "At producer" );
    	
    	String bootstrapServer = "172.28.5.75:9092";
    	String topic = "my_second_topic";
    	
    	//create properties
    	Properties props = new Properties();
    	props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
    	props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    	props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    	
    	
    	//create producer
    	KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
    	
    	for(int i=0; i<10; i++) {
    		//crate a producer record
        	ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, "key-"+i, "hello "+i);
        	
        	
        	//send the data - async operation
        	producer.send(producerRecord, new Callback() {
				
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					
					if(exception==null) {
						log.info("###Partition: "+ metadata.partition());
						log.info("Offset: "+ metadata.offset());
						log.info("Topic: "+ metadata.topic());
						log.info("Timestamp: "+ metadata.timestamp());
						
					}else {
						log.error("Excpetion while producing");
					}
					
				}
			});
        	
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    	//flush data - sync operation
    	producer.flush();
    	
    	//close - it calls flush by default
    	producer.close();
    	
    	
    }
}
 	