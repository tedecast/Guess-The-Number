/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data;

import com.sg.guessthenumber.TestApplicationConfiguration;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    }
    
    /**
     * Test of addGame method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGameGetGameByID() {
        
        Game game = new Game();
        game.setGameID(1);
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
       
        
        List<Game> games = this.dao.getAllGames();
        games.add(game);
        
        Game fromDao = this.dao.getGameByID(game.getGameID());
        assertEquals(game, fromDao);

        assertEquals(1, games.size());
        assertTrue(games.contains(game));
        

        
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setGameID(1);
        game.setWinningNumbers("3691");
        game.setGameStatus("IN PROGRESS");
        
        Game game2 = new Game();
        game.setGameID(2);
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
     * Test of getGameByID method, of class GameDatabaseDao.
     */
    @Test
    public void testGetGameByID() {
    }

    /**
     * Test of updateGame method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateGame() {
    }

    /**
     * Test of getRoundByID method, of class GameDatabaseDao.
     */
    @Test
    public void testGetRoundByID() {
    }

    /**
     * Test of addRound method, of class GameDatabaseDao.
     */
    @Test
    public void testAddRound() {
    }

    /**
     * Test of getAllRoundsByID method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllRoundsByID() {
    }
    
}
