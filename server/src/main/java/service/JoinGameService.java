package service;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import exception.ResponseException;

public class JoinGameService {
    private String color;
    private int id;
    public JoinGameService(String color, int id) {
        this.color = color;
        this.id = id;
    }

    public void join(String auth) throws ResponseException{
        if(this.color != "BLACK" && this.color != "WHITE" && this.color != null) {
            throw new ResponseException(400, "Error, bad request");
        }
        if(MemoryAuthDAO.authorize(auth)) {
            var usernam = MemoryAuthDAO.getUserfromAuth(auth);
            var game = new MemoryGameDAO();
            game.join(this.color, this.id, usernam);
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
