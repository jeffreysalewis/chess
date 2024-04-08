package webSocketMessages.userCommands;

public class Resign extends UserGameCommand{
    protected CommandType commandType = CommandType.RESIGN;
    private final String authToken;
    private int gameID;
    public Resign(String authToken, int gameID) {
        super(CommandType.RESIGN, authToken);
        this.authToken = authToken;
        this.gameID = gameID;
    }
}
