This simple plain java project demonstrates basic functioning of Kafka Producers and Consumers  

## Prerequisites  
* Running instances of kafka
* A Topic "my_second_topic" created with 3 Partitions preferably

## What can be seen in this project  
* A Producer send a message of format <String, String>
* A Consumer receives a message of format <String, String>
* Producer Callback function
* Consumer Group
* Topic
* A Consumer with Graceful shutdown  


###### Command to create topic
./kafka-topics.sh --bootstrap-server 172.28.5.75:9092 --create --topic my-second-topic --partitions 3
