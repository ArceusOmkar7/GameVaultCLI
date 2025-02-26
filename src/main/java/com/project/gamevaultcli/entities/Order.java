package com.project.gamevaultcli.entities;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private List<Game> games;
    private double totalAmount;
    private Date orderDate;

    public Order(int userId, List<Game> games, double totalAmount) {
        this.userId = userId;
        this.games = games;
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }

    public Order(int orderId, int userId, List<Game> games, double totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.games = games;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public Order() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}