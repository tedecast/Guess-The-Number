/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface GameDao {
    
    public Game addGame(Game game);
    
    public List<Game> getAllGames();
    
    public Game getGameByID(int gameID);
    
    public void updateGame(Game game);
    
    public Round getRoundByID(int roundID);
    
    public Round addRound(Round round);
    
    public List<Round> getAllRoundsByGameID(int gameID);
    
    public void deleteGameByID(int gameID);
    
    public void deleteRoundByID(int roundID);
    
    public List<Round> getAllRounds();
    
}
