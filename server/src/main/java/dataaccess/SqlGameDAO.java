package dataaccess;

import exception.ResponseException;

import java.util.Map;

public class SqlGameDAO implements GameDAO{
    public SqlGameDAO() {

    }

    @Override
    public int nueva(String name) {
        return 0;
    }

    @Override
    public void join(String color, int id, String username) throws ResponseException {

    }

    @Override
    public Map<String, String>[] list() {
        return new Map[0];
    }
}
