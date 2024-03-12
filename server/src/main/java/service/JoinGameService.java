package service;

import dataAccess.SqlAuthDAO;
import dataAccess.SqlGameDAO;
import exception.ResponseException;

public class JoinGameService {
    private String playerColor;
    private int gameID;
    public JoinGameService(String playerColor, int gameID) {
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public void join(String auth) throws ResponseException{
        try {
            if (SqlAuthDAO.authorize(auth)) {
                var usernam = SqlAuthDAO.getUserfromAuth(auth);
                var game = new SqlGameDAO();
                game.join(this.playerColor, this.gameID, usernam);
            } else {
                throw new ResponseException(401, "Error: unauthorized");
            }
        } catch (ResponseException r) {
            throw new ResponseException(r.StatusCode(), "Error: unauthorized");
        }
    }
}
