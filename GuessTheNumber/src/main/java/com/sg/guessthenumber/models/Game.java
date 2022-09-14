/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models;

import java.util.Objects;

/**
 *
 * @author Teresa
 */
public class Game {
    
    private int gameID;
    private String winningNumbers; 
    private String gameStatus;
    
    public Game() {
    }
    
    public Game(int gameID, String winningNumbers, String gameStatus) {
        this.gameID = gameID;
        this.winningNumbers = winningNumbers;
        this.gameStatus = gameStatus;
    }
    
    
    public Game(String winningNumbers, String gameStatus){
        this.winningNumbers = winningNumbers;
        this.gameStatus = gameStatus;
    }
    
    public Game(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(String winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.gameID;
        hash = 61 * hash + Objects.hashCode(this.winningNumbers);
        hash = 61 * hash + Objects.hashCode(this.gameStatus);
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
        final Game other = (Game) obj;
        if (this.gameID != other.gameID) {
            return false;
        }
        if (!Objects.equals(this.winningNumbers, other.winningNumbers)) {
            return false;
        }
        if (!Objects.equals(this.gameStatus, other.gameStatus)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameID=" + gameID + ", winningNumbers=" + winningNumbers + ", gameStatus=" + gameStatus + '}';
    }

}
