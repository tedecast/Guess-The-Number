/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data.service;

import com.sg.guessthenumber.TestApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;
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
    
    public GameServiceLayerImplTest() {
    }

    @Before
    public void setUp() {
    }
    

    /**
     * Test of createWinningNumbers method, of class GameServiceLayerImpl.
     */
    @Test
    public void testCreateWinningNumbers() {
    }

    /**
     * Test of beginGame method, of class GameServiceLayerImpl.
     */
    @Test
    public void testBeginGame() {
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
