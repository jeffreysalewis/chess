package dataAccess;

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

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `authtoken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              PRIMARY KEY (`username`),
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };
}
