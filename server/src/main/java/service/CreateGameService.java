package service;

import dataAccess.*;
import exception.*;

public class CreateGameService {
    private String name;
    public CreateGameService(String name) {
        this.name = name;
    }

    public int create(String auth) throws ResponseException {
        if(MemoryAuthDAO.authorize(auth)) {
            var game = new MemoryGameDAO();
            var gameid = game.nueva(this.name);
            return gameid;
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
