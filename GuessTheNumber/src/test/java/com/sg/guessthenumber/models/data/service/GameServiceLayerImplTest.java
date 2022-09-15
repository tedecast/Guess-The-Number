/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;


import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author Teresa
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceLayerImplTest {
    
    @Autowired
    GameServiceLayer service;   
    
    @Autowired
    JdbcTemplate jdbc;
    protected void clearTestingDB(){
        jdbc.execute("DELETE FROM round;");
        jdbc.execute("DELETE FROM game;");
    }
    
    @Before
    public void setUp() {
        clearTestingDB();
    }

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
    public void testBeginGameGetGameID() throws InvalidGameIDException {
        Game game = new Game();
        int gameID = this.service.beginGame();
        
        game = this.service.getGameByID(gameID);
        
        assertTrue(game.getGameStatus().equals("IN PROGRESS"));
        assertEquals(4, game.getWinningNumbers().length());     
        
        List<Game> games = this.service.getAllGames();
        games.add(game);
          
        game = this.service.getGameByID(game.getGameID());
        
        assertTrue(games.contains(game));
    }

    /**
     * Test of makeGuess method, of class GameServiceLayerImpl.
     */
    @Test
    public void testMakeGuessGetResult() throws Exception {  
        Game game = new Game();
        game.setGameID(1);
        game.setWinningNumbers("2368");
        game.setGameStatus("IN PROGRESS");
        
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

        Game game = new Game();
        game.setGameID(1);
        game.setWinningNumbers("2368");
        game.setGameStatus("IN PROGRESS");
        
        Game game2 = new Game();
        game.setGameID(2);
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        List<Game> games = this.service.getAllGames();
        games.add(game);
        games.add(game2);
        
        assertEquals(2, games.size()); 
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
        
    }

    /**
     * Test of getAllRoundsByGameID method, of class GameServiceLayerImpl.
     */
    @Test
    public void testGetAllRoundsByGameID() throws InvalidGameIDException {
        Game game = new Game();
        game.setGameID(1);
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        int gameID = this.service.beginGame();
        
        game = this.service.getGameByID(gameID);  
        
        List<Game> games = this.service.getAllGames();
        games.add(game);
          
        game = this.service.getGameByID(game.getGameID());
        
        assertTrue(games.contains(game));
        
        Round round = new Round();
        round.setGameID(game.getGameID());
        round.setRoundID(round.getRoundID());
        round.setGuess("1963");
        round.setResult("e:0:p:4");
        round.setGuessTime(LocalDateTime.now());
        
        Round round2 = new Round();
        round2.setGameID(game.getGameID());
        round2.setRoundID(round.getRoundID());
        round2.setGuess("3692");
        round2.setResult("e:3:p:1");
        round2.setGuessTime(LocalDateTime.now());
        
        List<Round> rounds = this.service.getAllRoundsByGameID(gameID);
        rounds.add(round);
        rounds.add(round2);
        
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
        assertEquals(2, rounds.size());
       
    }
    
    @Test
    public void finishedGameException() throws InvalidGameIDException, InvalidGuessException, FinishedGameException {
        
        try {
            Game game = new Game();
            game.setGameID(1);
            game.setWinningNumbers("3691");
            game.setGameStatus("IN PROGRESS");

            int gameID = this.service.beginGame();

            game = this.service.getGameByID(gameID);

            List<Game> games = this.service.getAllGames();
            games.add(game);

            assertTrue(games.contains(game));

            Round round = new Round();
            round.setGameID(game.getGameID());
            round.setRoundID(1);
            round.setGuess("3691");
            round.setResult("e:4:p:0");
            round.setGuessTime(LocalDateTime.now());
            
            round = this.service.makeGuess(round);
            
            game.setGameStatus("FINISHED"); 
            
            Round round2 = new Round();
            round2.setGameID(game.getGameID());
            round2.setRoundID(2);
            round2.setGuess("3692");
            round2.setResult("e:3:p:1");
            round2.setGuessTime(LocalDateTime.now());
            
            round2 = this.service.makeGuess(round2);
            
        } catch (FinishedGameException ex)  {
            assertTrue(true);
        }
    
    }
    
    @Test
    public void testGetInvalidGameID() throws InvalidGameIDException, FinishedGameException, InvalidGuessException {
        try {
            Game game = new Game();
            game.setGameID(1);
            game.setWinningNumbers("3691");
            game.setGameStatus("IN PROGRESS");
            game = this.service.getGameByID(5);

            Round round = new Round();
            round.setGameID(game.getGameID());
            round.setRoundID(10);
            round.setGuess("1963");
            round.setResult("e:0:p:4");
            round.setGuessTime(LocalDateTime.now());

            round = this.service.makeGuess(round);
            
            this.service.getAllRoundsByGameID(5);
            fail("InvalidGameIDException should be thrown");

        } catch (InvalidGameIDException ex) {
            assertTrue(true);
        }

    }
    
}
