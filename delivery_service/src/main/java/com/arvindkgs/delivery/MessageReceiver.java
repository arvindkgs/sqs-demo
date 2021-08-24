package com.arvindkgs.delivery;

/**
 *
 */

import com.arvindkgs.delivery.data.Order;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageReceiver {

    private final Gson gson;

    public MessageReceiver(Gson gson) {
        this.gson = gson;
    }

    @Async("threadPoolExecutor")
    @SqsListener(value = "${queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveStringMessage(final String message) throws Exception {
        Order order = gson.fromJson(message, Order.class);
        System.out.println("Thread: "+Thread.currentThread().getName()+": Delivering Order("+order.getOrderId()+")");
    }

}

