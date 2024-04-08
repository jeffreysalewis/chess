package webSocketMessages.userCommands;

public class JoinObserver extends UserGameCommand{
    protected CommandType commandType = CommandType.JOIN_OBSERVER;
    private final String authToken;
    private int gameID;
    public JoinObserver(String authToken, int gameID) {
        super(CommandType.JOIN_OBSERVER, authToken);
        this.authToken = authToken;
        this.gameID = gameID;
    }
}
