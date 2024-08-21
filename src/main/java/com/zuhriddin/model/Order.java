package com.zuhriddin.model;

import com.zuhriddin.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private int id;
    private Product product;
    private int orderAmount;
    private User user;
    private double totalPrice;
    private OrderStatus orderStatus;
}

