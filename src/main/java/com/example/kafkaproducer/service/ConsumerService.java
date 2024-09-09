package com.example.kafkaproducer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

/*
  kafka streams 를 사용할것으로 아래 구성은 사용 안함으로 주석처리함

  @KafkaListener(topics = "fastcampus", groupId = "foo")
  public void consumer(String message) {
    System.out.println(String.format("Subscried : %s", message));
  }*/
}
