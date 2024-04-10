package server.websocket;

import chess.*;

public class JuegaJson {
    private ChessGame.TeamColor turncolor;
    private ChessBoard gameboard;
    public JuegaJson(ChessGame.TeamColor turncolor, ChessBoard gameboard) {
        this.turncolor = turncolor;
        this.gameboard = gameboard;
    }

    public ChessGame.TeamColor getTurncolor() {
        return turncolor;
    }

    public ChessBoard getGameboard() {
        return gameboard;
    }
}
