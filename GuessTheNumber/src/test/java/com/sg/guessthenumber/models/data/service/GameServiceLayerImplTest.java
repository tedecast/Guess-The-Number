/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.data.GameDao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Teresa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameServiceLayerImplTest {
    
    @Autowired
    GameServiceLayerImpl service;   
    GameDao dao;
    
    
    public GameServiceLayerImplTest() {
        dao = new GameDatabaseDaoStubImpl();
        service = new GameServiceLayerImpl();
    }

    // At the start of each test method, here is the state of the HashMaps and Lists
    // Game(1, "3691", "IN PROGRESS");
    // Round(1, game.getGameID(), "1963", "e: 0  p: 4", LocalDateTime.now());
    

    /**
     * Test of createWinningNumbers method, of class GameServiceLayerImpl.
     */
    @Test
    public void testCreateWinningNumbers() {
  
        String numbers = this.service.createWinningNumbers();
        assertTrue(numbers.length() == 4);
    }

    /**
     * Test of beginGame method, of class GameServiceLayerImpl.
     */
    @Test
    public void testBeginGame() {
        Game game = new Game();
        game.setWinningNumbers(this.service.createWinningNumbers());
        game.setGameStatus("IN PROGRESS");
        
        int gameID = game.getGameID();
        gameID = this.service.beginGame();
        
        assertNotNull(gameID);
    }

    /**
     * Test of makeGuess method, of class GameServiceLayerImpl.
     */
    @Test
    public void testMakeGuess() throws Exception {
    }

    /**
     * Test of getRoundResult method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetRoundResult() {
    }

    /**
     * Test of getAllGames method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetAllGames() {
    }

    /**
     * Test of getGameByID method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetGameByID() {
    }

    /**
     * Test of getAllRoundsByGameID method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetAllRoundsByGameID() {
    }
    
}
