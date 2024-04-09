package server.websocket;

import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.*;
import exception.ResponseException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import webSocketMessages.serverMessages.*;
import webSocketMessages.userCommands.*;

import java.io.IOException;
import java.util.Map;


@WebSocket
public class WebSocketHandler {

    private final ConnectionManager connections = new ConnectionManager();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        UserGameCommand msg = new Gson().fromJson(message, UserGameCommand.class);
        switch (msg.getCommandType()) {
            case JOIN_PLAYER -> joinplayer(msg.getAuthString(), session, message);
            case JOIN_OBSERVER -> enter(msg.getAuthString(), session);
            case MAKE_MOVE -> exit(msg.getAuthString());
            case LEAVE -> exit(msg.getAuthString());
            case RESIGN -> exit(msg.getAuthString());
        }
    }

    private void joinplayer(String authToken, Session session, String message) throws IOException {
        connections.add(authToken, session);
        var notification = new Gson().fromJson(message, JoinPlayer.class);
        System.out.println(notification.getCommandType());
        //var notification = new Notification(message);
        String username = "";
        try {
            username = SqlAuthDAO.getUserfromAuth(authToken);
        } catch (ResponseException r) {
            System.out.println(r.getMessage());
        }
        //var notif = new Gson().toJson(Map.of("message", notification.toString()));
        var load = new LoadGame(new ChessGame());
        //var notif = new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME);
        var notif = new Notification(load.toString());
        connections.broadcast(null, load);
    }

    private void enter(String visitorName, Session session) throws IOException {
        connections.add(visitorName, session);
        var message = String.format("%s is in the shop", visitorName);
        //var notification = new UserGameCommand.CommandType(Notification.Type.ARRIVAL, message);
        //connections.broadcast(visitorName, notification);
    }

    private void exit(String visitorName) throws IOException {
//        connections.remove(visitorName);
//        var message = String.format("%s left the shop", visitorName);
//        var notification = new Notification(Notification.Type.DEPARTURE, message);
//        connections.broadcast(visitorName, notification);
    }

    public void makeNoise(String petName, String sound) throws ResponseException {
//        try {
//            var message = String.format("%s says %s", petName, sound);
//            var notification = new Notification(Notification.Type.NOISE, message);
//            connections.broadcast("", notification);
//        } catch (Exception ex) {
//            throw new ResponseException(500, ex.getMessage());
//        }
    }

    public void sendmessage(int gameID, String message, String authToken) {
        //var msg = new ServerMessage();
    }

    public void broadcastmessage(int gameID, String message, String authToken) {

    }
}
