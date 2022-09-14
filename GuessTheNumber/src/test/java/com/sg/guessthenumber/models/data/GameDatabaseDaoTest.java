/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
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
public class GameDatabaseDaoTest {
    
    
    @Autowired 
    GameDatabaseDao dao; 
    
    public GameDatabaseDaoTest() {
    }
    
    @Before 
    public void setUp() {
        List<Game> games = this.dao.getAllGames();
        
        for(Game game : games){
            this.dao.deleteGameByID(game.getGameID());
        }
        
        
        List<Round> rounds = this.dao.getAllRounds();

        for (Game game : games) {
            this.dao.deleteGameByID(game.getGameID());

            for (Round round : rounds) {

                this.dao.deleteRoundByID(game.getGameID(),round.getRoundID());
            }
        }
    }
    
    /**
     * Test of addGame method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGameGetGameByID() {
        
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        this.dao.addGame(game);
        
        Game fromDao = this.dao.getGameByID(game.getGameID());
        assertEquals(game, fromDao);
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        Game game2 = new Game();
        game.setWinningNumbers("2468");
        game.setGameStatus("IN PROGRESS");
        
        List<Game> games = this.dao.getAllGames();
        games.add(game);
        games.add(game2);

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of updateGame method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateGame() {
        
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        this.dao.addGame(game);
        
        Game fromDao = this.dao.getGameByID(game.getGameID());
        assertEquals(game, fromDao);
        
        game.setGameStatus("FINISHED");
        this.dao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
    }

    /**
     * Test of getRoundByID method, of class GameDatabaseDao.
     */
    @Test
    public void testAddRoundGetRoundByGameID() {
        
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        game = this.dao.addGame(game);
        
        Game gameDao = this.dao.getGameByID(game.getGameID());
        assertEquals(game, gameDao);
        
        Round round = new Round();
        round.setGameID(game.getGameID());
        round.setGuess("3691");
        round.setResult("e: 4  p: 0");
        round.setGuessTime(LocalDateTime.now());       
        round = this.dao.addRound(round);

        assertNotNull(this.dao.getRoundByID(round.getRoundID()));
    }


    /**
     * Test of getAllRounds method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        List<Game> games = this.dao.getAllGames();
        games.add(game);
        assertEquals(1, games.size());
        assertTrue(games.contains(game));
        
        Round round = new Round();
        round.setGameID(game.getGameID());
        round.setGuess("3692");
        round.setResult("e: 3  p: 0");
        round.setGuessTime(LocalDateTime.now()); 
        
        Round round2 = new Round();
        round2.setGameID(game.getGameID());
        round2.setGuess("1963");
        round2.setResult("e: 0  p: 4");
        round2.setGuessTime(LocalDateTime.now());      
          
        Game game2 = new Game();
        game2.setWinningNumbers("2468");
        game2.setGameStatus("IN PROGRESS");
        
        Round round3 = new Round();
        round3.setGameID(game2.getGameID());
        round3.setGuess("3692");
        round3.setResult("e: 3  p: 0");
        round3.setGuessTime(LocalDateTime.now()); 

        List<Round> rounds = this.dao.getAllRounds();
        rounds.add(round);
        rounds.add(round2);
        rounds.add(round3);
        
        assertEquals(3, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
        assertTrue(rounds.contains(round3));
        
    }
    
     /**
     * Test of getAllRoundsByID method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllRoundsByGameID() {
    
        Game game = new Game();
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        List<Game> games = this.dao.getAllGames();
        games.add(game);
        assertEquals(1, games.size());
        assertTrue(games.contains(game));
        
        Round round = new Round();
        round.setGameID(game.getGameID());
        round.setGuess("3692");
        round.setResult("e: 3  p: 0");
        round.setGuessTime(LocalDateTime.now()); 
        
        Round round2 = new Round();
        round2.setGameID(game.getGameID());
        round2.setGuess("1963");
        round2.setResult("e: 0  p: 4");
        round2.setGuessTime(LocalDateTime.now());       
        
        List<Round> rounds = this.dao.getAllRoundsByGameID(game.getGameID());
        rounds.add(round);
        rounds.add(round2);
        
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }
    
}
