/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models;

import java.time.LocalDateTime;


/**
 *
 * @author Teresa
 */
public class Round {
    
    private int roundID;
    private int gameID;
    private String guess;
    private String result;
    private LocalDateTime guessTime;
    
    public Round() {
    }
    
    public Round(int roundID, int gameID, String guess, String result, LocalDateTime guessTime) {
        this.roundID = roundID;
        this.gameID = gameID;
        this.guess = guess;
        this.result = result;
        this.guessTime = guessTime;
    }

    public int getRoundID() {
        return roundID;
    }
    
    // may not need setter
    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }
}
