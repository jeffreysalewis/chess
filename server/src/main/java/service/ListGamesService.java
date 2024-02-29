package service;

import chess.*;

import java.util.*;

public class ListGamesService {
    private String authtoken;

    public ListGamesService(String auth) {
        this.authtoken = auth;
    }

    public Collection<ChessGame> getgames() {
        return new HashSet<ChessGame>();
    }
}
