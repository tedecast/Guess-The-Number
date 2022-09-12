/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface GameServiceLayer {
    
    public String createWinningNumbers();
    
    public Game beginGame();
    
    public Round makeGuess(Round guess);
    
    public String getResult(String guess, String answer);
    
    public List<Game> getAllGames();
    
    public Game getGamesByID(int gameID);
    
    public Round getRoundsByID(int gameID);
    
    
    
}
