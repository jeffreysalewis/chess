package webSocketMessages.userCommands;

import chess.*;

public class MakeMove extends UserGameCommand{
    private int gameID;
    private ChessMove move;
    public MakeMove(String authToken, int gameID, ChessMove move) {
        super(CommandType.MAKE_MOVE, authToken);
        this.gameID = gameID;
        this.move = move;
    }

    public int getGameID() {
        return gameID;
    }

    public ChessMove getMove() {
        return move;
    }
}
