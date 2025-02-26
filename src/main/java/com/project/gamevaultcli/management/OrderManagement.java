/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.management;

import com.project.gamevaultcli.entities.Cart;
import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.entities.Order;
import com.project.gamevaultcli.entities.User;
import com.project.gamevaultcli.exceptions.CartEmptyException;
import com.project.gamevaultcli.exceptions.OrderNotFoundException;
import com.project.gamevaultcli.storage.CartStorage;
import com.project.gamevaultcli.storage.OrderStorage;
import com.project.gamevaultcli.storage.UserStorage;

import java.util.List;

public class OrderManagement {

    private final OrderStorage orderStorage;
    private final CartStorage cartStorage;
    private final UserStorage userStorage;

    public OrderManagement(OrderStorage orderStorage, CartStorage cartStorage, UserStorage userStorage) {
        this.orderStorage = orderStorage;
        this.cartStorage = cartStorage;
        this.userStorage = userStorage;
    }

    public Order getOrder(int orderId) throws OrderNotFoundException {
        Order order = orderStorage.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return orderStorage.findAll();
    }

    public void placeOrder(int userId) throws CartEmptyException {
        Cart cart = cartStorage.findById(userId);
        if (cart == null || cart.getGames().isEmpty()) {
            throw new CartEmptyException("Cart is empty for user: " + userId);
        }

        List<Game> games = cart.getGames();
        double totalAmount = games.stream().mapToDouble(Game::getPrice).sum();

        Order order = new Order(userId, games, totalAmount);
        orderStorage.save(order);

        // Clear the cart after placing the order
        cart.getGames().clear();
        cartStorage.update(cart);

        //Potentially manage user wallet balance
        User user = userStorage.findById(userId);
        if (user != null) {
            user.setWalletBalance((float) (user.getWalletBalance() - totalAmount));
            userStorage.update(user);
        }
    }
}
