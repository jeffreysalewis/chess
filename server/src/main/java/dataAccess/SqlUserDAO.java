package dataAccess;

import exception.ResponseException;

import java.sql.SQLException;
import java.util.HashMap;

public class SqlUserDAO implements UserDAO{
    public static HashMap<String, String[]> userdata = new HashMap<>();
    public SqlUserDAO () throws ResponseException{
        try{
            configureDatabase();
        } catch (Exception ex) {
            throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
        updateuserdata();
    }

    @Override
    public String[] getUser(String username) throws ResponseException {
        try (var blah = DatabaseManager.getConnection()) {
            try (var tempcuenta = blah.prepareStatement("SELECT username, password, email FROM user WHERE username=?")) {
                tempcuenta.setString(1, username);
                try (var rs = tempcuenta.executeQuery()) {
                    rs.next();
                    var us = rs.getString("username");
                    var pd = rs.getString("password");
                    var em = rs.getString("email");

                    System.out.printf("us: %s, pd: %s, em: %s%n", us, pd, em);
                    updateuserdata();
                    return new String[]{us, pd, em};
                }  catch (Exception e) {
                    return null;
                    //throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
        }
    }
    @Override
    public void createUser (String username, String password, String email) throws ResponseException {
        try (var blah = DatabaseManager.getConnection()) {
            try (var tempcuenta = blah.prepareStatement("INSERT INTO user (username, password, email) VALUES(?, ?, ?)")) {
                tempcuenta.setString(1, username);
                tempcuenta.setString(2, password);
                tempcuenta.setString(3, email);

                tempcuenta.executeUpdate();

            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to insert user in database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to insert user in database: %s", e.getMessage()));
        }
        updateuserdata();
    }

    public static void clear() throws ResponseException{
        try (var blah = DatabaseManager.getConnection()) {
            try (var tempcuenta = blah.prepareStatement("TRUNCATE user")) {
                tempcuenta.executeUpdate();
                SqlUserDAO.userdata.clear();
            } catch(Exception e) {
                throw new ResponseException(500, String.format("Unable to clear user in database: %s", e.getMessage()));
            }
        } catch(Exception r) {
            throw new ResponseException(500, String.format("Unable to clear user in database: %s", r.getMessage()));
        }
    }

    private void updateuserdata() {
        try (var blah = DatabaseManager.getConnection()) {
            try (var tempcuenta = blah.prepareStatement("SELECT username, password, email FROM user")) {
                try (var rs = tempcuenta.executeQuery()) {
                    while(rs.next()) {
                        var us = rs.getString("username");
                        var ps = rs.getString("password");
                        var em = rs.getString("email");
                        String[] ac = {us, ps, em};
                        SqlUserDAO.userdata.put(us, ac);
                    }
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  user (
              `username` varchar(256) NOT NULL,
              `password` varchar(256) NOT NULL,
              `email` varchar(256) NOT NULL,
              PRIMARY KEY (`username`)
            )
            """
    };

    private void configureDatabase() throws Exception {
        DatabaseManager.createDatabase();
        var ac = "ac";
        try (var blah = DatabaseManager.getConnection()) {
            for (var statement : createStatements) {
                try (var tempcuenta = blah.prepareStatement(statement)) {
                    tempcuenta.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new ResponseException(501, String.format("Error: can't config the database: %s", ex.getMessage()));
        }
    }
}

