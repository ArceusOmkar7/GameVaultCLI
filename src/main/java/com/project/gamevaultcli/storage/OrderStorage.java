/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.storage;

import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.entities.Order;
import com.project.gamevaultcli.interfaces.StorageInterface;

import java.util.*;

public class OrderStorage implements StorageInterface<Order, Integer>{

    private final Map<Integer, Order> orders = new HashMap<>(); // In-memory storage

    public OrderStorage() {
        // Initialize with some hardcoded data
        List<Game> games1 = new ArrayList<>();
        games1.add(new Game(1, "Game 1", "Description 1", "Developer 1", "PC", 20.0f, null));
        orders.put(1, new Order(1, 1, games1, 20.0, new Date()));
    }

    @Override
    public Order findById(Integer orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void save(Order order) {
        int nextId = orders.keySet().stream().max(Integer::compare).orElse(0) + 1;
        order.setOrderId(nextId);
        orders.put(nextId, order);
    }

    @Override
    public void update(Order order) {
        // Assuming the order already exists.  If not, this will overwrite.
        orders.put(order.getOrderId(), order);
    }

    @Override
    public void delete(Integer orderId) {
        orders.remove(orderId);
    }
}
