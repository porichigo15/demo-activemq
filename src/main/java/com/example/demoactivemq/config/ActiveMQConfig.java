package com.example.demoactivemq.config;

import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class ActiveMQConfig {
    @Setter
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Setter @Value("${spring.activemq.user}")
    private String user;

    @Setter @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setPassword(password);
        connectionFactory.setUserName(user);
        return connectionFactory;
    }
}
