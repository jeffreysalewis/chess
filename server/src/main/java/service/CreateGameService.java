package service;

import dataAccess.*;
import exception.*;

public class CreateGameService {
    private String gameName;
    public CreateGameService(String gameName) {
        this.gameName = gameName;
    }

    public int create(String auth) throws ResponseException {
        try {
            if (SqlAuthDAO.authorize(auth)) {
                var game = new SqlGameDAO();
                var gameid = game.nueva(this.gameName);
                return gameid;
            } else {
                throw new ResponseException(401, "Error: unauthorized");
            }
        } catch (ResponseException r) {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
