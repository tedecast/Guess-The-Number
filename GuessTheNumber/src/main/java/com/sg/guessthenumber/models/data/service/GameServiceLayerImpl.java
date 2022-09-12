/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.sg.guessthenumber.models.data.GameDao;

/**
 *
 * @author Teresa
 */
public class GameServiceLayerImpl implements GameServiceLayer {
    
    private static final int numbers = 4;
    private static final Random random = new Random();
    
    private final GameDao dao;
    
    @Autowired
    public GameServiceLayerImpl(GameDao dao){
        this.dao = dao;
    }
    
    @Override
    public String createWinningNumbers() {
        // access randomly?
        Set<Integer> nums = new HashSet<>();
        String numToAdd = "";
        while (nums.size() < numbers) {
            Integer toAdd = random.nextInt(10);
            nums.add(toAdd);
        }
        
        List<Integer> numList = new ArrayList<>(nums);
        Collections.shuffle(numList); // shuffle a list of numbers to get random?
        for (int i = 0; i < numbers; i++){
            numToAdd += numList.get(i);
        }
        return numToAdd;
    }
    
    @Override
    public Game beginGame() {
        
        Game game = new Game();
        game.setWinningNumbers(this.createWinningNumbers());
        this.dao.addGame(game);
        return game;
    }
    
    @Override
    public Round makeGuess(Round round){
        
        String answer = this.dao.getGameByID(round.getGame().getGameID()).getWinningNumbers();
        String guess = round.getGuess();
        String result = this.getResult(guess, answer);
        round.setResult(result);
        
        Game game = this.getGamesByID(round.getGame().getGameID());
        if (guess.equalsIgnoreCase(answer)) {  
            game.setProgress("FINISHED");
            this.dao.updateGame(game);
        } else { 
            game.setProgress("IN PROGRESS");
        }
        
        return this.dao.addRound(round);
        
    }
    
    @Override
    public String getResult(String guess, String answer) {
        char[] guessChar = guess.toCharArray();
        char[] answerChar = answer.toCharArray();
        int exact = 0;
        int partial = 0;
        
        for (int i = 0; i < guessChar.length; i++){
            
            // index starts at 0, meaning it's exact. 
            if (answer.indexOf(guessChar[i]) > -1) {
                if(guessChar[i] == answerChar[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }       
        }
        String result = "e: " + exact + " p: " + partial;
        return result;  
    }

    @Override
    public List<Game> getAllGames() {
        
        List<Game> games = this.dao.getAllGames();
        for (Game game : games) {
            if(game.getProgress().equalsIgnoreCase("IN PROGRESS")) {
                game.setWinningNumbers("****");
            }
        }
        
        return games;
    }

    @Override
    public Game getGamesByID(int gameID) {
        Game game = this.dao.getGameByID(gameID);
        if (game.getProgress().equalsIgnoreCase("IN PROGRESS")) {
            game.setWinningNumbers("****");
        }
        
        return game;
    }

    @Override
    public Round getRoundsByID(int gameID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
