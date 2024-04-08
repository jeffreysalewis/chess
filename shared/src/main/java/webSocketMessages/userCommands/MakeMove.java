package webSocketMessages.userCommands;

public class MakeMove extends UserGameCommand{
    protected CommandType commandType = CommandType.MAKE_MOVE;
    private final String authToken;
    private int gameID;
    public MakeMove(String authToken, int gameID) {
        super(CommandType.MAKE_MOVE, authToken);
        this.authToken = authToken;
        this.gameID = gameID;
    }
}
