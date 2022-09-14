/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models;

import java.time.LocalDateTime;
import java.util.Objects;


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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.roundID;
        hash = 83 * hash + this.gameID;
        hash = 83 * hash + Objects.hashCode(this.guess);
        hash = 83 * hash + Objects.hashCode(this.result);
        hash = 83 * hash + Objects.hashCode(this.guessTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundID != other.roundID) {
            return false;
        }
        if (this.gameID != other.gameID) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundID=" + roundID + ", gameID=" + gameID + ", guess=" + guess + ", result=" + result + ", guessTime=" + guessTime + '}';
    }
    
}
