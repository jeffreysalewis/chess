package dataaccess;

import exception.ResponseException;

import java.util.Map;

public interface GameDAO {
    public int nueva(String name);
    public void join(String color, int id, String username) throws ResponseException;
    public Map<String, String>[] list();
}
