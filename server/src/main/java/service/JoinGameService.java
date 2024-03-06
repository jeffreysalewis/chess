package service;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import exception.ResponseException;

public class JoinGameService {
    private String playerColor;
    private int gameID;
    public JoinGameService(String playerColor, int gameID) {
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public void join(String auth) throws ResponseException{
//        if(this.playerColor != "BLACK" && this.playerColor != "WHITE" && this.playerColor != null) {
//            throw new ResponseException(400, "Error, bad request");
//        }
        if(MemoryAuthDAO.authorize(auth)) {
            var usernam = MemoryAuthDAO.getUserfromAuth(auth);
            var game = new MemoryGameDAO();
            game.join(this.playerColor, this.gameID, usernam);
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
