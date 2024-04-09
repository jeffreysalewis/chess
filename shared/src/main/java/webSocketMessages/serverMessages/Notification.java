package webSocketMessages.serverMessages;

public class Notification extends ServerMessage{
    private String message;

    public Notification() {
        super(ServerMessageType.NOTIFICATION);
    }
}
