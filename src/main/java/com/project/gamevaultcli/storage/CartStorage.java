package com.project.gamevaultcli.storage;

import com.project.gamevaultcli.entities.Cart;
import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.interfaces.StorageInterface;  // Import the interface

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartStorage implements StorageInterface<Cart, Integer> {  // Implement the interface

    private final Map<Integer, Cart> carts = new HashMap<>(); // In-memory storage

    public CartStorage() {
        // Initialize with some hardcoded data
        List<Game> games1 = new ArrayList<>();
        games1.add(new Game(1, "Game 1", "Description 1", "Developer 1", "PC", 20.0f, null));
        carts.put(1, new Cart(1, games1));

        carts.put(2, new Cart(2, new ArrayList<>())); // Empty cart for user 2
    }

    @Override
    public Cart findById(Integer userId) {
        return carts.get(userId);
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(carts.values());
    }

    @Override
    public void save(Cart cart) {
        carts.put(cart.getUserId(), cart);
    }

    @Override
    public void update(Cart cart) {
        // Assuming the cart already exists.  If not, this will overwrite.
        carts.put(cart.getUserId(), cart);
    }

    @Override
    public void delete(Integer userId) {
        carts.remove(userId);
    }
}
