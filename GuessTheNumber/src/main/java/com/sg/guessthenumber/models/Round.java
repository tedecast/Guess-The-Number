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
public class Round {
    
    private int roundID;
    private int roundNumber;
    private String guess;
    private String results;
    
    public Round() {
    }

    public int getRoundID() {
        return roundID;
    }
    
    // may not need setter
    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }


    
    
}
