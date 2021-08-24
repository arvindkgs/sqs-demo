package com.arvindkgs.order.controller;

import com.arvindkgs.order.data.Item;
import com.arvindkgs.order.data.Order;
import com.arvindkgs.order.service.OrderDispatcher;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.ArrayList;

@Controller
public class OrderController {

    private final OrderDispatcher orderDispatcher;

    public OrderController(OrderDispatcher orderDispatcher) {
        this.orderDispatcher = orderDispatcher;
    }

    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity createOrder(@RequestBody Order order) {
        orderDispatcher.send(order);
        return ResponseEntity.ok().body(null);
    }
}
