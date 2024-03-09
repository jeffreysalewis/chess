package dataAccess;

import com.google.gson.Gson;
import exception.ResponseException;

import java.sql.SQLException;
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
                    preparedStatement.setInt(1, 0);
                    preparedStatement.setString(2, "");
                    preparedStatement.setString(3, "");
                    preparedStatement.setString(4, name);
                    var gamejson = new Gson().toJson(new ChessGame());
                    preparedStatement.setString(5, gamejson);

                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to create authtoken: %s", e.getMessage()));
        }
        return 0;
    }

    @Override
    public void join(String color, int id, String username) throws ResponseException {

    }

    @Override
    public Map<String, String>[] list() {
        return new Map[0];
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  game (
              `gameID` int NOT NULL,
              `whiteUsername` varchar(256) NOT NULL,
              `blackUsername` varchar(256) NOT NULL,
              `gameName` varchar(256) NOT NULL,
              `gamejson` text NOT NULL,
              PRIMARY KEY (`gameID`),
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
