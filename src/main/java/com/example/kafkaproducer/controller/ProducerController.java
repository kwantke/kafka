package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

 private final Producer producer;

  @PostMapping("/message")
  public void publishMessage(@RequestParam String msg) {
    //producer.pub(msg);
  }
}
