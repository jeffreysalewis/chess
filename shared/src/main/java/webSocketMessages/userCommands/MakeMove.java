package webSocketMessages.userCommands;

public class MakeMove extends UserGameCommand{
    private int gameID;
    public MakeMove(String authToken, int gameID) {
        super(CommandType.MAKE_MOVE, authToken);
        this.gameID = gameID;
    }
}
