package com.example.kafkaproducer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<String, Object>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    Map<String, Object> myConfig = new HashMap<>();
    myConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092, 192.168.56.102:9092, 192.168.56.103:9092");
    myConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    myConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // 추가로 설정 항목
    //myConfig.put(ProducerConfig.BATCH_SIZE_CONFIG);
    // Apache Kafka의 Producer 설정 중 하나로, 프로듀서가 한 번에 전송할 메시지의 최대 배치 크기를 바이트 단위로 지정합니다. 이 설정은 메시지를 전송하기 전에 얼마나 많은 메시지를 배치로 모아서 보낼지를 결정합니다.
    //batch.size = 32,768 (32 KB): 프로듀서는 최대 32KB까지의 메시지를 모아서 한 번에 전송합니다.
    //이점: 배치 크기를 늘리면, 더 많은 메시지를 한 번에 전송하므로, 네트워크 호출 횟수를 줄일 수 있습니다. 이는 처리량을 높이고, 네트워크 대역폭을 효율적으로 사용할 수 있습니다.
    //주의: 배치 크기가 너무 크면 메시지 전송이 지연될 수 있습니다. 또한, 프로듀서가 설정된 배치 크기만큼 메시지를 모으지 못하면, linger.ms 설정에 따라 지연이 발생하기 전까지 배치를 기다리게 됩니다.



    //myConfig.put(ProducerConfig.LINGER_MS_CONFIG)
    //Apache Kafka의 Producer 설정 중 하나로, 메시지를 배치로 보내는 동안 지연 시간을 지정하는 데 사용됩니다. 이 설정은 메시지를 얼마나 오랫동안 배치에 모아서 보내는지를 결정합니다.
    //linger.ms = 5: 프로듀서는 메시지가 도착하면 최대 5밀리초 동안 대기하며, 이 동안 다른 메시지가 도착하면 배치를 구성합니다. 5밀리초가 지나거나 배치 크기가 batch.size를 초과하면, 배치를 전송합니다.
    //이점: 배치를 통해 전송할 메시지가 많아지면, 전송 횟수가 줄어들어 성능이 향상될 수 있습니다. 특히 네트워크 대역폭을 절약하고, 전송 효율을 높일 수 있습니다.
    //주의: 너무 큰 값으로 설정하면 메시지 전송 지연이 발생할 수 있습니다. 이는 낮은 레이턴시가 중요한 실시간 애플리케이션에서는 적합하지 않을 수 있습니다.


    return new DefaultKafkaProducerFactory<>(myConfig);
  }

  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    Map<String, Object> myConfig = new HashMap<>();
    myConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092, 192.168.56.102:9092, 192.168.56.103:9092");
    myConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    myConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);


    return new DefaultKafkaConsumerFactory<>(myConfig);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> myFactory = new ConcurrentKafkaListenerContainerFactory<>();
    myFactory.setConsumerFactory(consumerFactory());

    return myFactory;
  }
}
