package webSocketMessages.serverMessages;

public class Error extends ServerMessage{
    private String errorMessage;

    public Error() {
        super(ServerMessageType.ERROR);
    }
}
