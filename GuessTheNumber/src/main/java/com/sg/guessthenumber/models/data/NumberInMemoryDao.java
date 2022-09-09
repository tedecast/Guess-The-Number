/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Teresa
 */
@Repository
public class NumberInMemoryDao implements NumberDao {
    
    private static final List<Game> games = new ArrayList<>();
    private static final List<Round> rounds = new ArrayList<>();

    @Override
    public Game addGame(Game game) {
        
        int nextID = games.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;
        
        game.setId(nextID);
        games.add(game);
        return game;
        
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    @Override
    public Game getGameByID(int gameID) {
        return games.stream()
                .filter(i -> i.getId() == gameID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Round addRound(Round round) {
        int nextRound = rounds.stream()
                .mapToInt(r -> r.getRoundNumber())
                .max()
                .orElse(0) + 1;

        round.setRoundNumber(nextRound);
        rounds.add(round);
        return round;
    }

    @Override
    public List<Round> getAllRounds() {
        return new ArrayList<>(rounds);
    }
    
}
