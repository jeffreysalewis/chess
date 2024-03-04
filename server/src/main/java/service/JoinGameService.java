package service;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import exception.ResponseException;

public class JoinGameService {
    private String color;
    private String id;
    public JoinGameService(String color, String id) {
        this.color = color;
        this.id = id;
    }

    public void join(String auth) throws ResponseException{
        if(MemoryAuthDAO.authorize(auth)) {
            var game = new MemoryGameDAO();
            game.join();
            return;
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
