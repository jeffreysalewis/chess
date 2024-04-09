package webSocketMessages.serverMessages;

import chess.ChessGame;

public class LoadGame extends ServerMessage {
    private ChessGame game;

    public LoadGame() {
        super(ServerMessageType.LOAD_GAME);
    }
}
