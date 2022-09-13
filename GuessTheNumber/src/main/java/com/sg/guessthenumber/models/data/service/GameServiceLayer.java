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
    
    public int beginGame();
    
    public Round makeGuess(Round guess) throws FinishedGameException, InvalidGameIDException, InvalidGuessException;
    
    public String getRoundResult(String guess, String answer);
    
    public List<Game> getAllGames();
    
    public Game getGameByID(int gameID);
    
    public List<Round> getAllRoundsByID(int gameID);
    
    
    
}
