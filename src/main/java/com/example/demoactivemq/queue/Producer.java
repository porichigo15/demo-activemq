package com.example.demoactivemq.queue;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final Logger log = LoggerFactory.getLogger(Producer.class);

    @Value("${spring.activemq.topic}")
    private String topic;

    @Autowired
    @Setter
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        try {
            log.info(" ");
            log.info("=========== SEND MESSAGE ACTIVE MQ ============");
            log.info("[TOPIC] {}", topic);
            log.info("[REQUEST] {}", message);
            log.info(" ");

            jmsTemplate.convertAndSend(topic, message);
        } catch (Exception e) {
            log.info(" ");
            log.info("=========== SEND MESSAGE ACTIVE MQ ============");
            log.error("[ERROR] {}", e.toString());
            log.info(" ");
        }
    }
}
