package com.arvindkgs.order.data;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Order {
    public Order(int orderId, Instant orderCreated, List<Item> items) {
        this.orderId = orderId;
        this.orderCreated = orderCreated;
        this.items = items;
    }

    private final int orderId;
    private final Instant orderCreated;
    private final List<Item> items;
}
