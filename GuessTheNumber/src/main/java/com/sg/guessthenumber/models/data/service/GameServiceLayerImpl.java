/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.models.data.NumberDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Teresa
 */
public class GameServiceLayerImpl implements GameServiceLayer {
    
    private static final int numbers = 4;
    private static final Random random = new Random();
    
    private final NumberDao numberDao;
    
    @Autowired
    public GameServiceLayerImpl(NumberDao numberDao){
        this.numberDao = numberDao;
    }
    
    @Override
    public String createWinningNumbers() {
        // access randomly?
        Set<Integer> nums = new HashSet<>();
        String numToAdd = "";
        while (nums.size() < numbers) {
            Integer toAdd = random.nextInt();
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
    public Game begin() {
        
        Game game = new Game();
        game.setWinningNumbers(this.createWinningNumbers());
        this.numberDao.addGame(game);
        return game;
    }
    
    @Override
    public Round guess(Round guess){
        
        List<Game> games = this.numberDao.getAllGames();
        Round round = new Round();
        
        Round result = new Round();
        int exact = 0;
        int partial = 0;
        
        return result;     
    }

    @Override
    public List<Game> getAllGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getGamesByID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getRoundsByID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
