package dataAccess;

import exception.ResponseException;

import java.util.Map;

public interface GameDAO {
    public int nueva(String name) throws ResponseException;
    public void join(String color, int id, String username) throws ResponseException;
    public Map<String, String>[] list() throws ResponseException;
}
