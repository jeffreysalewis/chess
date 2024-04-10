package webSocketMessages.userCommands;

public class JoinPlayer extends UserGameCommand{
    private int gameID;
    private String playerColor;
    public JoinPlayer(String authToken, int gameID, String playerColor) {
        super(CommandType.JOIN_PLAYER, authToken);
        this.gameID = gameID;
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public String getPlayerColor() {
        return playerColor;
    }
}
