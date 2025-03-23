/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.management;

import com.project.gamevaultcli.entities.Cart;
import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.exceptions.CartEmptyException;
import com.project.gamevaultcli.storage.CartStorage;

import java.util.List;

public class CartManagement {

    private final CartStorage cartStorage;

    public CartManagement(CartStorage cartStorage) {
        this.cartStorage = cartStorage;
    }

    public Cart getCart(int userId) {
        Cart cart = cartStorage.findById(userId);
        if (cart == null) {
            cart = new Cart(userId); // Create a new cart if it doesn't exist
            cartStorage.save(cart);
        }
        return cart;
    }

    public void addGameToCart(int userId, Game game) {
        Cart cart = getCart(userId);
        cart.addGame(game);
        cartStorage.update(cart); // Update the cart in storage
    }

    public void removeGameFromCart(int userId, Game game) {
        Cart cart = getCart(userId);
        cart.removeGame(game);
        cartStorage.update(cart);
    }

    public List<Game> getGamesInCart(int userId) throws CartEmptyException {
        Cart cart = getCart(userId);
        List<Game> games = cart.getGames();
        if (games.isEmpty()) {
            throw new CartEmptyException("Cart is empty for user: " + userId);
        }
        return games;
    }
}
