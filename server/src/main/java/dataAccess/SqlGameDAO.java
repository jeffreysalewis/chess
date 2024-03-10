package dataAccess;

import com.google.gson.Gson;
import exception.ResponseException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import chess.*;

public class SqlGameDAO implements GameDAO{
    public SqlGameDAO() throws Exception{
        configureDatabase();
    }

    @Override
    public int nueva(String name) throws ResponseException{
        try {
            this.configureDatabase();
            try (var conn = DatabaseManager.getConnection()) {
                try (var preparedStatement = conn.prepareStatement("INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, gamejson) VALUES(?, ?, ?, ?, ?)")) {
                    int gamid = (int) (Math.random()*1000000);
                    preparedStatement.setInt(1, gamid);
                    preparedStatement.setString(2, "");
                    preparedStatement.setString(3, "");
                    preparedStatement.setString(4, name);
                    var gamejson = new Gson().toJson(new ChessGame());
                    preparedStatement.setString(5, gamejson);

                    preparedStatement.executeUpdate();
                    return gamid;
                } catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
        }
    }

    @Override
    public void join(String color, int id, String username) throws ResponseException {
        try {
            this.configureDatabase();
            try (var conn = DatabaseManager.getConnection()) {
                String updatestr;
                if(color == "WHITE") {
                    updatestr = "UPDATE game SET whiteUsername=? WHERE gameID=?";
                } else if(color == "BLACK") {
                    updatestr = "UPDATE game SET blackUsername=? WHERE gameID=?";
                } else {
                    try (var preparedStatement = conn.prepareStatement(" SELECT gameID, whiteUsername, blackUsername, gameName, gamejson FROM game WHERE gameID=?")) {
                        preparedStatement.setInt(1, id);
                        var rs = preparedStatement.executeQuery();
                        rs.next();
                        var gid = rs.getInt("gameID");
                    } catch (Exception e) {
                        throw new ResponseException(500, String.format("Unable to join: %s", e.getMessage()));
                    }
                    return;
                }
                try (var preparedStatement = conn.prepareStatement(updatestr)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setInt(2, id);

                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to join: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to join: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
        }
    }

    @Override
    public Map<String, String>[] list() throws ResponseException{
        int len = 0;
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("SELECT gameID, whiteUsername, blackUsername, gameName, gamejson FROM game")) {
                try (var rs = preparedStatement.executeQuery()) {
                    while(rs.next()) {
                        len++;
                    }
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
        }
        var bettergamelist = new HashMap[len];
        len = 0;
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("SELECT gameID, whiteUsername, blackUsername, gameName, gamejson FROM game")) {
                try (var rs = preparedStatement.executeQuery()) {
                    while(rs.next()) {
                        var id = rs.getInt("gameID");
                        var wu = rs.getString("whiteUsername");
                        var bu = rs.getString("blackUsername");
                        var na = rs.getString("gameName");
                        var gj = rs.getString("gamejson");
                        bettergamelist[len] = new HashMap<String, String>();
                        bettergamelist[len].put("gameID", id);
                        bettergamelist[len].put("whiteUsername", wu);
                        bettergamelist[len].put("blackUsername", bu);
                        bettergamelist[len].put("gameName", na);
                        len++;
                    }
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
        }
        return bettergamelist;
    }

    public static void clear() throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("TRUNCATE game")) {
                preparedStatement.executeUpdate();
            } catch(Exception e) {
                throw new ResponseException(500, String.format("Unable to clear auth in database: %s", e.getMessage()));
            }
        } catch(Exception r) {
            throw new ResponseException(500, String.format("Unable to clear auth in database: %s", r.getMessage()));
        }
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  game (
              `gameID` int NOT NULL,
              `whiteUsername` varchar(256) NOT NULL,
              `blackUsername` varchar(256) NOT NULL,
              `gameName` varchar(256) NOT NULL,
              `gamejson` text NOT NULL,
              PRIMARY KEY (`gameID`)
            )
            """
    };

    private void configureDatabase() throws Exception {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }
}
