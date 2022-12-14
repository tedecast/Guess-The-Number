/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.models.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Teresa
 */
@Repository
public class GameDatabaseDao implements GameDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    @Transactional 
    public Game addGame(Game game) {
        
        final String sql = "INSERT INTO game(winningNumbers, gameStatus) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, game.getWinningNumbers());
            statement.setString(2, game.getGameStatus());
            return statement;
            
        }, keyHolder);
        
        game.setGameID(keyHolder.getKey().intValue());
        
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * from game;";
        return this.jdbcTemplate.query(sql, new GameMapper());
    }
    
    
    @Override
    public Game getGameByID(int gameID) {

        final String sql = "SELECT * FROM game WHERE gameID = ?;";

        return this.jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);

    }
    
    @Override
    public void updateGame(Game game) {
        final String sql = "UPDATE game SET gamestatus = ? WHERE gameID = ?";
        this.jdbcTemplate.update(sql, game.getGameStatus(), game.getGameID());
    }
    
    
    @Override
    public Round getRoundByID(int roundID) {
        
        final String sql = "SELECT * FROM round WHERE roundID = ?;";
        
        return this.jdbcTemplate.queryForObject(sql, new RoundMapper(), roundID);
    }
    
    @Override
    @Transactional
    public Round addRound(Round round) {
                
        final String sql = "INSERT INTO round(gameid, guess, result, guesstime) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, round.getGameID());
            statement.setString(2, round.getGuess());
            statement.setString(3, round.getResult());
            statement.setTimestamp(4, Timestamp.valueOf(round.getGuessTime()));
            return statement;
            
        }, keyHolder);
        
        round.setRoundID(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public List<Round> getAllRoundsByGameID(int gameID) {
        final String sql = "SELECT * from round "
                + "WHERE gameID = ? ORDER BY guesstime";
        List<Round> rounds = this.jdbcTemplate.query(sql, new RoundMapper(), gameID);
        return rounds;
    }

    @Override
    public void deleteGameByID(int gameID) {
        final String delete_round = "DELETE FROM round WHERE gameid = ?";
        this.jdbcTemplate.update(delete_round, gameID);
        
        final String delete_game = "DELETE FROM game WHERE gameid = ?";
        this.jdbcTemplate.update(delete_game, gameID);
    }
    
    @Override
    public void deleteRoundByID(int gameID, int roundID) {
        
        final String delete_round = "DELETE FROM round WHERE gameid = ? AND roundid = ?";
        this.jdbcTemplate.update(delete_round, gameID, roundID);
    }

    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT * from round;";
        return this.jdbcTemplate.query(sql, new RoundMapper());
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("gameid"));
            game.setWinningNumbers(rs.getString("winningnumbers"));
            game.setGameStatus(rs.getString("gamestatus"));
            return game;

        }
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(rs.getInt("roundid"));
            round.setGameID(rs.getInt("gameid"));
            round.setGuess(rs.getString("guess"));
            round.setResult(rs.getString("result"));
            
            Timestamp timestamp = rs.getTimestamp("guesstime");
            round.setGuessTime(timestamp.toLocalDateTime());
            
            return round;

        }
    }
}
