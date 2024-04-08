package webSocketMessages.userCommands;

public class Leave extends UserGameCommand{
    protected CommandType commandType = CommandType.LEAVE;
    private final String authToken;
    private int gameID;
    public Leave(String authToken, int gameID) {
        super(CommandType.LEAVE, authToken);
        this.authToken = authToken;
        this.gameID = gameID;
    }
}
