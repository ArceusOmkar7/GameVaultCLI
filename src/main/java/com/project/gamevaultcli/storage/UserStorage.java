/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.gamevaultcli.storage;

import com.project.gamevaultcli.entities.User;
import com.project.gamevaultcli.interfaces.StorageInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage implements StorageInterface<User, Integer>{

    private final Map<Integer, User> users = new HashMap<>(); // In-memory storage

    public UserStorage() {
        // Initialize with some hardcoded data
        users.put(1, new User(1, "sasuke@gmail.com", "is this my password?", "Sasuke", 50.0f, new Date()));
        users.put(2, new User(2, "naruto@gmail.com", "believeit", "Naruto", 100.0f, new Date()));
    }

    @Override
    public User findById(Integer userId) {
        return users.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(User user) {
        int nextId = users.keySet().stream().max(Integer::compare).orElse(0) + 1;
        user.setUserId(nextId);
        users.put(nextId, user);
    }

    @Override
    public void update(User user) {
        // Assuming the user already exists. If not, this will overwrite.
        users.put(user.getUserId(), user);
    }

    @Override
    public void delete(Integer userId) {
        users.remove(userId);
    }
}