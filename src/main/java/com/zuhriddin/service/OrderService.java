package com.zuhriddin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zuhriddin.model.Order;
import com.zuhriddin.enumeration.OrderStatus;
import com.zuhriddin.service.util.UtilityJson;
import com.zuhriddin.service.util.UtilityTxt;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements BaseService<Order, Integer> {


    private static final String ORDER_ID = "src/file/orderId.txt";
    private static final String PATH = "src/file/orders.json";


    @Override
    public Order add(Order order) {
        List<Order> orders = read();
        calculateTotalPrice(order);
        orders.add(orderIds(order));
        write(orders);
        return order;
    }
 public Order orderIds(Order order){

         String PATH_I = "src/test/order_id.txt";
         int id = Integer.parseInt(UtilityTxt.read(PATH_I));
         order.setId(id);
         UtilityTxt.write(String.valueOf(id + 1), PATH_I);
         return order;
     }

    public void calculateTotalPrice(Order order) {
            double totalPrice = order.getProduct().getPrice() * order.getOrderAmount();
            order.setTotalPrice(totalPrice);
    }

    public Order get(Integer id) {
        return read().stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Agent not found."));
    }

    @Override
    public void delete(Integer id) {
        List<Order> orders = read();
        orders = orders.stream()
                .filter(order -> order.getId() != id)
                .collect(Collectors.toList());
        write(orders);
    }


    @Override
    public List<Order> list() {
        List<Order> orders = read();
        return orders;
    }

    // Method : buyurtmaning xolati o'zgarganini bilish
    public void updateOrderStatus(Integer orderId, OrderStatus newStatus) {
        List<Order> orders = read();
        orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .ifPresent(order -> order.setOrderStatus(newStatus));
        write(orders);
    }

    // Method to list orders by status
    public List<Order> listOrdersByStatus(OrderStatus status) {
        List<Order> orders = read();
        return orders.stream()
                .filter(order -> order.getOrderStatus() == status)
                .collect(Collectors.toList());
    }


    private List<Order> read() {
        return UtilityJson.read(PATH, new TypeReference<>() {
        });
    }

    private void write(List<Order> orders) {
        UtilityJson.write(PATH, orders);
    }
}
