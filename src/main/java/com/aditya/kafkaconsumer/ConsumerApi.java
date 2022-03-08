package com.aditya.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerApi {

    List<String> messages = new ArrayList<>();
    User user1 = null;

    @GetMapping("/consumestr")
    public List<String> consumeMessage(){
        return messages;
    }

    @GetMapping("/consumejson")
    public User consumeJSonMessage(){
        return user1;
    }

    @KafkaListener(groupId = "mygroup",topics = "aditya",containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMessageFromTopic(String data){
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "mygroup-2",topics = "aditya",containerFactory = "userKafkaListenerContainerFactory")
    public User getUserMessageFromTopic(User user){
        user1 = user;
        return user1;
    }
}
