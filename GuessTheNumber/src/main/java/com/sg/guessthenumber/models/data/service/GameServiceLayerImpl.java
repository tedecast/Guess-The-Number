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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Teresa
 */
@Service
public class GameServiceLayerImpl implements GameServiceLayer {
    
    private static final int numbers = 4;
    private static final Random random = new Random();
    
    @Autowired
    GameDao dao;
    
    @Override
    public String createWinningNumbers() {
        // access randomly?
        Set<Integer> nums = new HashSet<>();
        String numToAdd = "";
        while (nums.size() < numbers) {
            Integer toAdd = random.nextInt(10); // number from 0-9 
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
    public int beginGame() {
        
        Game game = new Game();
        game.setWinningNumbers(this.createWinningNumbers());
        game.setGameStatus("IN PROGRESS");
        this.dao.addGame(game);
        return game.getGameID();
    }
    
    @Override
    public Round makeGuess(Round round) throws FinishedGameException, InvalidGameIDException, InvalidGuessException {
        try {
            String answer = this.dao.getGameByID(round.getGameID()).getWinningNumbers();
            String guess = round.getGuess();
            String result = this.getRoundResult(guess, answer);

            LocalDateTime guessTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            round.setGuessTime(guessTime);
            round.setGameID(round.getGameID());
            round.setResult(result);

            Game game = this.getGameByID(round.getGameID());

            if (guess.equalsIgnoreCase(answer)) {
                game.setGameStatus("FINISHED");
                this.dao.updateGame(game);
            }

            if (game.getGameStatus().equalsIgnoreCase("FINISHED")) {

                throw new FinishedGameException("This Game is now FINISHED. "
                        + "Please Begin a New Game or choose a Game that is IN PROGRESS.");
            }

            if (guess.length() != 4 || guess == null) {
                throw new InvalidGuessException("ERROR: Please try again and enter a guess that consists of exactly 4 valid numbers.");

            }

        } catch (DataAccessException ex) {
            throw new InvalidGameIDException ("ERROR: No such GameID exists. Please try again and enter a valid GameID.");
        }

        return this.dao.addRound(round);

    }
    
    @Override
    public String getRoundResult(String guess, String answer) {
        char[] guessChar = guess.toCharArray();
        char[] answerChar = answer.toCharArray();
        int exact = 0;
        int partial = 0;
        String result = "";
        
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
        
        result = "e: " + exact + "  p: " + partial;
        return result;  
    }

    @Override
    public List<Game> getAllGames() {
        
        List<Game> games = this.dao.getAllGames();
        for (Game game : games) {
            if(game.getGameStatus().equalsIgnoreCase("IN PROGRESS")) {
                game.setWinningNumbers("****");
            }
        }
        
        return games;
    }

    @Override
    public Game getGameByID(int gameID) {
        
        Game game = this.dao.getGameByID(gameID);
        if (game.getGameStatus().equalsIgnoreCase("IN PROGRESS")) {
            game.setWinningNumbers("****");
        }
        
        return game;
    }

    @Override
    public List<Round> getAllRoundsByID(int gameID) {
        
        return this.dao.getAllRoundsByID(gameID);
    }

}
