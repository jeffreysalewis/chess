package webSocketMessages.serverMessages;

import chess.ChessGame;
import com.google.gson.Gson;

import java.util.Map;

public class LoadGame extends ServerMessage {
    private ChessGame game;

    public LoadGame(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        System.out.println("asdfdsafdsfa");
        this.game = game;
    }

    @Override
    public String toString() {
        //String s = new Gson().toJson(Map.ofEntries(Map.entry("serverMessageType", ServerMessageType.LOAD_GAME), Map.entry("game", this.game)));
        String s = new Gson().toJson(this);
        System.out.println(this.serverMessageType);
        System.out.println("fdjskalfjdlkjf");
        return s;
    }
}
