package com.example.demoactivemq.queue;

import com.example.demoactivemq.model.Item;
import com.example.demoactivemq.model.QueueMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Listener {
    private final Logger log = LoggerFactory.getLogger(Listener.class);

    @Value("${spring.active-mq.topic}")
    private String topic;

    @JmsListener(destination = "${spring.activemq.topic}")
    @SendTo("${spring.activemq.topic}")
    public void receiveMessage(Message message) {
        try {
            String messageData = this.getTextMessage(message);
            if (messageData != null) {
                QueueMessage result = new Gson().fromJson(messageData, QueueMessage.class);

                log.info(" ");
                log.info("=========== RECEIVE MESSAGE ACTIVE MQ ============");
                log.info("[TOPIC] {}", topic);
                log.info("[RESPONSE] {}", messageData);
                log.info(" ");

                if (result != null) {
                    System.out.println("Name: " + result.getName());
                    for (Item item: result.getItemList()) {
                        System.out.println("Item name: " + item.getName());
                        System.out.println("Item description: " + item.getDescription());
                    }
                }
            }
        } catch (Exception e) {
            log.info(" ");
            log.info("=========== RECEIVE MESSAGE ACTIVE MQ ============");
            log.error("[ERROR] {}", e.toString());
            log.info(" ");
        }
    }

    private String getTextMessage(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            return textMessage.getText();
        }

        return null;
    }
}
