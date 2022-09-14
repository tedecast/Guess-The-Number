/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models;

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
    
    

 
}
