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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Teresa
 */
@Repository
public class GameDatabaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Game addGame(Game game) {
        
        final String sql = "INSERT INTO game(winningNumbers) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, game.getWinningNumbers());
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
        
        final String sql = "SELECT * "
                + "FROM game WHERE gameID = ?;";
        
        return this.jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
    }
    
    @Override
    public void updateGame(Game game) {
        final String sql = "UPDATE game SET progress = ? WHERE gameID = ?";
        this.jdbcTemplate.update(sql, game.getProgress(), game.getGameID());
    }
    
    
    @Override
    public Round getRoundByID(int roundID) {
        
        final String sql = "SELECT * "
                + "FROM rounds WHERE roundID = ?;";
        
        return this.jdbcTemplate.queryForObject(sql, new RoundMapper(), roundID);
    }
    
    @Override
    public Round addRound(Round round) {
                
        final String sql = "INSERT INTO rounds(guess) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, round.getGuess());
            return statement;
            
        }, keyHolder);
        
        round.setRoundID(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT * from rounds;";
        return this.jdbcTemplate.query(sql, new RoundMapper());
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("gameid"));
            game.setWinningNumbers(rs.getString("winningnumbers"));
            game.setProgress(rs.getString("progress"));
            return game;

        }
    }

        private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundID(index);
            round.getGame().setGameID(rs.getInt("gameid"));
            round.setRoundNumber(rs.getInt("roundNumber"));
            round.setGuess(rs.getString("guess"));
            round.setResult(rs.getString("result"));
            return round;

        }
    }
}
