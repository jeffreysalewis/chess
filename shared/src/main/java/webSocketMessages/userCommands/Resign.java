package webSocketMessages.userCommands;

public class Resign extends UserGameCommand{
    private int gameID;
    public Resign(String authToken, int gameID) {
        super(CommandType.RESIGN, authToken);
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }
}
