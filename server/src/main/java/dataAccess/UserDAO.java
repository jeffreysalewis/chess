package dataAccess;

import exception.ResponseException;

public interface UserDAO {
    public Object getUser(String username) throws ResponseException;
    public void createUser(String username, String password, String email) throws ResponseException;

}
