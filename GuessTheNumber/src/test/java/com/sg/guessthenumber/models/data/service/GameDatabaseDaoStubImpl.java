/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.models.data.GameDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Teresa
 */
public class GameDatabaseDaoStubImpl implements GameDao {
    
    private Map<Integer, Game> games = new HashMap<>();
    private Map<Integer, Round> rounds = new HashMap<>();
    private List<Game> gameList = new ArrayList<>();
    private List<Round> roundList = new ArrayList<>();
    

    public GameDatabaseDaoStubImpl() {
        Game game = new Game(1, "3691", "IN PROGRESS");
        games.put(game.getGameID(), game);
        //gameList.add(game);
        
        Round round1 = new Round(1, game.getGameID(), "1963", "e:0:p:4", LocalDateTime.now());
        rounds.put(round1.getRoundID(), round1);   
        //roundList.add(round1);
    }
    

    @Override
    public Game addGame(Game game) {
        return games.put(game.getGameID(), game);
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList(games.values());
    }

    @Override
    public Game getGameByID(int gameID) {
        return games.get(gameID);
    }

    @Override
    public void updateGame(Game game) {
        this.games.replace(game.getGameID(), game);
    }

    @Override
    public Round getRoundByID(int roundID) {
        return rounds.get(roundID);
    }

    @Override
    public Round addRound(Round round) {
        return rounds.put(round.getRoundID(), round);
    }

    
    @Override
    public List<Round> getAllRoundsByGameID(int gameID) {
        List<Round> selectedRound = roundList.stream()
                .filter(r -> r.getGameID() == gameID)
                .collect(Collectors.toList());
        //Round round = selectedRound.get(0); 
        return selectedRound;
    }

    @Override
    public void deleteGameByID(int gameID) {
        games.remove(gameID);
    }

    @Override
    public void deleteRoundByID(int gameID, int roundID) {
        rounds.remove(roundID);
        games.remove(gameID);
        
    }

    @Override
    public List<Round> getAllRounds() {
        return new ArrayList(rounds.values());
    }
    
}
