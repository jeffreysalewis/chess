package dataAccess;

import com.google.gson.Gson;
import exception.ResponseException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import chess.*;

public class SqlGameDAO implements GameDAO{
    public SqlGameDAO() throws ResponseException{
        try{
            configureDatabase();
        } catch (Exception ex) {
            throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }

    @Override
    public int nueva(String name) throws ResponseException{
        try {
            this.configureDatabase();
            try (var aeccion = DatabaseManager.getConnection()) {
                try (var sp = aeccion.prepareStatement("INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, gamejson, observers) VALUES(?, ?, ?, ?, ?, ?)")) {
                    int gamid = (int) (Math.random()*1000000);
                    sp.setInt(1, gamid);
                    sp.setString(2, "valwasnull");
                    sp.setString(3, "valwasnull");
                    //sp.setNull(2, Types.VARCHAR);
                    //sp.setNull(3, Types.VARCHAR);
                    if(name != null) {
                        sp.setString(4, name);
                    } else {
                        sp.setString(4, "valwasnull");
                        //sp.setNull(4, Types.VARCHAR);
                    }
                    var gamejson = new Gson().toJson(new ChessGame());
                    sp.setString(5, gamejson);
                    sp.setString(6, "observers: ");
                    sp.executeUpdate();
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
            try (var aeccion = DatabaseManager.getConnection()) {
                String updatestr = "";
                if("WHITE".equals(color)) {
                    if(doescolorhaveplayer(color, id)) {
                        updatestr = "UPDATE game SET whiteUsername=? WHERE gameID=?";
                    } else {
                        throw new ResponseException(403, "Error: forbidden");
                    }
                } else if("BLACK".equals(color)) {
                    if(doescolorhaveplayer(color, id)) {
                        updatestr = "UPDATE game SET blackUsername=? WHERE gameID=?";
                    } else {
                        throw new ResponseException(403, "Error: forbidden");
                    }
                } else {
                    updatestr = "UPDATE game SET observers=? WHERE gameID=?";
                    try (var sp = aeccion.prepareStatement(" SELECT gameID, whiteUsername, blackUsername, gameName, gamejson, observers FROM game WHERE gameID=?")) {
                        sp.setInt(1, id);
                        var rs = sp.executeQuery();
                        rs.next();
                        var observerlist = rs.getString("observers");
                        username = observerlist + "," + username;
                    } catch (Exception e) {
                        throw new ResponseException(400, String.format("Unable to join: %s", e.getMessage()));
                    }
                    //return;
                }
                try (var sp = aeccion.prepareStatement(updatestr)) {
                    sp.setString(1, username);
                    sp.setInt(2, id);

                    sp.executeUpdate();
                } catch (Exception e) {
                    throw new ResponseException(400, String.format("Unable to join: %s", e.getMessage()));
                }
            } catch (ResponseException r) {
                throw new ResponseException(r.statusCode(), String.format("Unable to join: %s", r.getMessage()));
            }
        } catch(ResponseException re){
            throw new ResponseException(re.statusCode(), String.format("Unable to join: %s", re.getMessage()));
        } catch (Exception e) {
            throw new ResponseException(400, String.format("Unable to create authtoken: %s", e.getMessage()));
        }
    }

    @Override
    public Map<String, String>[] list() throws ResponseException{
        int len = 0;
        try (var aeccion = DatabaseManager.getConnection()) {
            try (var sp = aeccion.prepareStatement("SELECT gameID, whiteUsername, blackUsername, gameName, gamejson, observers FROM game")) {
                try (var rs = sp.executeQuery()) {
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
        try (var aeccion = DatabaseManager.getConnection()) {
            try (var sp = aeccion.prepareStatement("SELECT gameID, whiteUsername, blackUsername, gameName, gamejson, observers FROM game")) {
                try (var rs = sp.executeQuery()) {
                    while(rs.next()) {
                        var id = rs.getInt("gameID");
                        var wu = rs.getString("whiteUsername");
                        var bu = rs.getString("blackUsername");
                        var na = rs.getString("gameName");
                        var gj = rs.getString("gamejson");
                        var ob = rs.getString("observers");
                        if(wu.equals("valwasnull")) {
                            wu = null;
                        }
                        if(bu.equals("valwasnull")) {
                            bu = null;
                        }
                        if(na.equals("valwasnull")) {
                            na = null;
                        }
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
        try (var aeccion = DatabaseManager.getConnection()) {
            try (var sp = aeccion.prepareStatement("TRUNCATE game")) {
                sp.executeUpdate();
            } catch(Exception e) {
                throw new ResponseException(500, String.format("Unable to clear auth in database: %s", e.getMessage()));
            }
        } catch(Exception r) {
            throw new ResponseException(500, String.format("Unable to clear auth in database: %s", r.getMessage()));
        }
    }

    private boolean doescolorhaveplayer(String color, int id) throws ResponseException{
        try (var aeccion = DatabaseManager.getConnection()) {
            try (var sp = aeccion.prepareStatement("SELECT whiteUsername, blackUsername FROM game WHERE gameID=?")) {
                sp.setInt(1, id);
                try (var rs = sp.executeQuery()) {
                    rs.next();
                    var wu = rs.getString("whiteUsername");
                    var bu = rs.getString("blackUsername");

                    if(wu.equals("valwasnull") && "WHITE".equals(color)) {
                        return true;
                    } else if(bu.equals("valwasnull") && "BLACK".equals(color)) {
                        return true;
                    } else {
                        return false;
                    }
                }  catch (Exception e) {
                    throw new ResponseException(403, String.format("Unable to authorize: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(403, String.format("Unable to authorize: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(400, String.format("Error: forbidden %s", e.getMessage()));
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
              `observers` text NOT NULL,
              PRIMARY KEY (`gameID`)
            )
            """
    };

    private void configureDatabase() throws Exception {
        DatabaseManager.createDatabase();
        try (var aeccion = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var sp = aeccion.prepareStatement(statement)) {
                    var hdd = "hdd";
                    sp.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new ResponseException(502, String.format("Error: no puedo a configure database: %s", ex.getMessage()));
        }
    }
}
