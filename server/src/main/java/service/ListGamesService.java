package service;

import dataAccess.SqlAuthDAO;
import dataAccess.SqlGameDAO;
import exception.ResponseException;

import java.util.*;

public class ListGamesService {

    public ListGamesService() {
    }

    public Map<String, String>[] getgames(String auth) throws ResponseException {
        try {
            if (SqlAuthDAO.authorize(auth)) {
                var game = new SqlGameDAO();
                var gamelist = game.list();
                return gamelist;
            } else {
                throw new ResponseException(401, "Error: unauthorized");
            }
        } catch (ResponseException r) {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
