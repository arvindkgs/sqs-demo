package com.arvindkgs.order.service;

import com.arvindkgs.order.data.Order;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderDispatcher {

    private final QueueMessagingTemplate queueMessagingTemplate;

    private Gson gson;

    OrderDispatcher(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        gson = new Gson();
    }

    @Value("${queue-name}")
    private String queueName;

    public void send(Order order) {
        String msg = gson.toJson(order);
        final Message<String> event = MessageBuilder.withPayload(msg).build();
        this.queueMessagingTemplate.send(queueName, event);
    }
}
