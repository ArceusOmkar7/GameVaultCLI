package com.project.gamevaultcli.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int userId;
    private List<Game> games;

    public Cart(int userId) {
        this.userId = userId;
        this.games = new ArrayList<>();
    }

    public Cart(int userId, List<Game> games) {
        this.userId = userId;
        this.games = games;
    }

    public Cart() {}

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public int getUserId() { return userId; }
    public List<Game> getGames() { return games; }
}