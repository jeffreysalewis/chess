package webSocketMessages.userCommands;

public class JoinPlayer extends UserGameCommand{
    protected CommandType commandType = CommandType.JOIN_PLAYER;
    private final String authToken;
    private int gameID;
    private String playerColor;
    public JoinPlayer(String authToken, int gameID, String playerColor) {
        super(CommandType.JOIN_PLAYER, authToken);
        this.authToken = authToken;
        this.gameID = gameID;
        this.playerColor = playerColor;
    }
}
