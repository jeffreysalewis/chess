package dataAccess;

import exception.ResponseException;

import java.sql.SQLException;
import java.util.UUID;

public class SqlAuthDAO implements AuthDAO{
    public SqlAuthDAO() throws ResponseException{
        try{
            configureDatabase();
        } catch (Exception ex) {
            throw new ResponseException(500, String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }

    @Override
    public String createAuth(String username) throws ResponseException{
        var authtoken = UUID.randomUUID().toString();
        try {
            this.configureDatabase();
            try (var conn = DatabaseManager.getConnection()) {
                try (var preparedStatement = conn.prepareStatement("INSERT INTO auth (authtoken, username) VALUES(?, ?)")) {
                    preparedStatement.setString(1, authtoken);
                    preparedStatement.setString(2, username);

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
        return authtoken;
    }

    @Override
    public String[] getUser(String username) throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("SELECT authtoken, username FROM auth WHERE username=?")) {
                preparedStatement.setString(1, username);
                try (var rs = preparedStatement.executeQuery()) {
                    rs.next();
                    var au = rs.getString("authtoken");
                    var us = rs.getString("username");

                    System.out.printf("au: %s, us: %s", au, us);
                    return new String[]{us, au};
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
        }
    }

    public static boolean authorize(String inpauth) throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("SELECT authtoken, username FROM auth WHERE authtoken=?")) {
                preparedStatement.setString(1, inpauth);
                try (var rs = preparedStatement.executeQuery()) {
                    rs.next();
                    var au = rs.getString("authtoken");
                    var us = rs.getString("username");

                    //System.out.printf("au: %s, us: %s", au, us);
                    if(!us.isBlank()) {
                        return true;
                    } else {
                        return false;
                    }
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to authorize: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to authorize: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(401, String.format("Unable to authorize: %s", e.getMessage()));
        }
    }

    public static String getUserfromAuth(String auth) throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("SELECT authtoken, username FROM auth WHERE authtoken=?")) {
                preparedStatement.setString(1, auth);
                try (var rs = preparedStatement.executeQuery()) {
                    rs.next();
                    var au = rs.getString("authtoken");
                    var us = rs.getString("username");

                    System.out.printf("au: %s, us: %s", au, us);
                    return us;
                }  catch (Exception e) {
                    throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
                }
            } catch (Exception e) {
                throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
            }
        } catch (Exception e) {
            throw new ResponseException(500, String.format("Unable to get user from database: %s", e.getMessage()));
        }
    }

    public static void deleteAuth(String auth) throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("DELETE FROM auth WHERE authtoken=?")) {
                preparedStatement.setString(1, auth);
                preparedStatement.executeUpdate();
            } catch(Exception e) {
                throw new ResponseException(500, String.format("Unable to delete auth in database: %s", e.getMessage()));
            }
        } catch(Exception r) {
            throw new ResponseException(500, String.format("Unable to delte auth in database: %s", r.getMessage()));
        }
    }

    public static void clear() throws ResponseException{
        try (var conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("TRUNCATE auth")) {
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
            CREATE TABLE IF NOT EXISTS  auth (
              `authtoken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              PRIMARY KEY (`authtoken`)
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
