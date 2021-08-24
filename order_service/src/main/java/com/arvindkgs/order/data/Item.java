package com.arvindkgs.order.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class
Item {
    private String product;
    private Double price;
    private Integer quantity;
}
