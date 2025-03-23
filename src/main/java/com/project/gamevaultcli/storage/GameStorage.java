
package com.project.gamevaultcli.storage;

import com.project.gamevaultcli.entities.Game;
import com.project.gamevaultcli.interfaces.StorageInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStorage implements StorageInterface<Game, Integer>{

    private final Map<Integer, Game> games = new HashMap<>(); // In-memory storage

    public GameStorage() {
        // Initialize with some hardcoded data
        games.put(1, new Game(1, "Spider-Man Remastered", "Game created ?? lol lorem ipsum or wot", "Insomniac Games", "PC", 52.3f, new Date()));
        games.put(2, new Game(2, "God of War", "A great game", "Santa Monica Studio", "PS4", 49.99f, new Date()));
    }

    @Override
    public Game findById(Integer gameId) {
        return games.get(gameId);
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games.values());
    }

    @Override
    public void save(Game game) {
        int nextId = games.keySet().stream().max(Integer::compare).orElse(0) + 1;
        game.setGameId(nextId);
        games.put(nextId, game);
    }

    @Override
    public void update(Game game) {
        // Assuming the game already exists. If not, this will overwrite.
        games.put(game.getGameId(), game);
    }

    @Override
    public void delete(Integer gameId) {
        games.remove(gameId);
    }
}
