package service;

import chess.*;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import exception.ResponseException;

import java.util.*;

public class ListGamesService {

    public ListGamesService() {
    }

    public Map<String, String>[] getgames(String auth) throws ResponseException {
        if(MemoryAuthDAO.authorize(auth)) {
            var game = new MemoryGameDAO();
            var gamelist = game.list();
            return gamelist;
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
