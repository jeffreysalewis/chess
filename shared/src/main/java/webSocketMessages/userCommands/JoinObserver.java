package webSocketMessages.userCommands;

public class JoinObserver extends UserGameCommand{
    private int gameID;
    public JoinObserver(String authToken, int gameID) {
        super(CommandType.JOIN_OBSERVER, authToken);
        this.gameID = gameID;
    }
}
