package com.example.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

/*
 kafka streams 를 사용할것으로 아래 구성은 사용 안함으로 주석처리함

 String topicName = "fastcampus";

  private KafkaTemplate<String, Object> kafkaTemplate;
  @Autowired
  public Producer(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
  public void pub(String msg) {
    kafkaTemplate.send(topicName, msg);
  }*/
}
