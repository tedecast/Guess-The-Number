/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.models.data.GameDao;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
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
    GameDatabaseDaoStubImpl dao;
    
//    @Autowired
//    public GameServiceLayerImplTest() {
//        dao = new GameDatabaseDaoStubImpl();
//        service = new GameServiceLayerImpl();
//    }

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
//        Game game = new Game();
//        game.setWinningNumbers("1234");//(this.service.createWinningNumbers());
//        game.setGameStatus("IN PROGRESS");
//        
//        int gameID = game.getGameID();
//        gameID = this.service.beginGame();
//        
//        assertNotNull(gameID);
        
    }

    /**
     * Test of makeGuess method, of class GameServiceLayerImpl.
     */
    @Test
    public void testMakeGuessGetResult() throws Exception {  
        Game game = new Game();
        game.setGameID(game.getGameID());
        game.setWinningNumbers("2368");
        game.setGameStatus("IN PROGRESS");
        
//        int gameID = game.getGameID();
//        gameID = this.service.beginGame();
//        
//        assertNotNull(gameID);
        
        Round round = new Round();
        round.setGameID(game.getGameID());
        round.setRoundID(round.getRoundID());
        round.setGuess("8632");
        round.setResult("e:0:p:4");
        round.setGuessTime(LocalDateTime.now());
        
        String roundResult = this.service.getRoundResult(round.getGuess(), game.getWinningNumbers());
        
        assertThat(roundResult, is(equalTo(round.getResult())));
        
    }
    
    public void testMakeInvalidGuess() throws Exception, InvalidGuessException {  

        try {
            Game game = new Game();
            game.setGameID(2);
            game.setWinningNumbers("2368");
            game.setGameStatus("IN PROGRESS");

            int gameID = game.getGameID();
            gameID = this.service.beginGame();

            assertNotNull(gameID);
            
            Round round = new Round();
            round.setGameID(game.getGameID());
            round.setRoundID(round.getRoundID());
            round.setGuess("skjda");
            round.setResult(this.service.getRoundResult(round.getGuess(), game.getWinningNumbers()));
            round.setGuessTime(LocalDateTime.now());
            
            round = this.service.makeGuess(round);
            fail("InvalidGameIDException should be thrown");
        } catch (InvalidGuessException ex) {
            assertTrue(true);
        }

    }

    /**
     * Test of getAllGames method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetAllGames() {
//        
//        List<Game> games = this.service.getAllGames();
//        
//        assertEquals(1, games.size()); 
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
