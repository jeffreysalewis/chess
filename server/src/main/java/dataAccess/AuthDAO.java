package dataAccess;

import exception.ResponseException;

public interface AuthDAO {
    public String createAuth(String username) throws ResponseException;
    public String[] getUser(String username) throws ResponseException;
}
