package server.websocket;

import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.*;
import exception.ResponseException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import webSocketMessages.serverMessages.*;
import webSocketMessages.serverMessages.Error;
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
        String username = "";
        try {
            username = SqlAuthDAO.getUserfromAuth(authToken);
            var gdao = new SqlGameDAO();
            var juego = gdao.getGame(notification.getGameID());
            var jnum = juego.get("gameID");
            if(jnum == null) {
                throw new ResponseException(500, "Error: error");
            }
            var iden = Integer.parseInt(juego.get("gameID"));
            if(iden != notification.getGameID()) {
                throw new ResponseException(500, "Error: error");
            }
            if(notification.getPlayerColor().equals("WHITE")) {
                if(!username.equals(juego.get("whiteUsername"))) {
                    throw new ResponseException(500, "Error: error");
                }
            } else if (notification.getPlayerColor().equals("BLACK")) {
                if(!username.equals(juego.get("blackUsername"))) {
                    throw new ResponseException(500, "Error: error");
                }
            } else {
                throw new ResponseException(500, "Error: error");
            }
        } catch (ResponseException r) {
            ServerMessage er = new Error("error: error");
            connections.broadcast1(authToken, er);
            System.out.println(r.getMessage());
            return;
        }
        //var notif = new Gson().toJson(Map.of("message", notification.toString()));
        var load = new LoadGame(new ChessGame());
        //var notif = new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME);
        var notif = new Notification(load.toString());
        connections.broadcast1(authToken, load);
        connections.broadcast(authToken, notif);
    }

    private void joinobserver(String authToken, Session session, String message) throws IOException {
        connections.add(authToken, session);
        var notification = new Gson().fromJson(message, JoinPlayer.class);
        String username = "";
        try {
            username = SqlAuthDAO.getUserfromAuth(authToken);
            var gdao = new SqlGameDAO();
            var juego = gdao.getGame(notification.getGameID());
            var jnum = juego.get("gameID");
            if(jnum == null) {
                throw new ResponseException(500, "Error: error");
            }
            var iden = Integer.parseInt(juego.get("gameID"));
            if(iden != notification.getGameID()) {
                throw new ResponseException(500, "Error: error");
            }
            if(notification.getPlayerColor().equals("WHITE")) {
                if(!username.equals(juego.get("whiteUsername"))) {
                    throw new ResponseException(500, "Error: error");
                }
            } else if (notification.getPlayerColor().equals("BLACK")) {
                if(!username.equals(juego.get("blackUsername"))) {
                    throw new ResponseException(500, "Error: error");
                }
            } else {
                throw new ResponseException(500, "Error: error");
            }
        } catch (ResponseException r) {
            ServerMessage er = new Error("error: error");
            connections.broadcast1(authToken, er);
            System.out.println(r.getMessage());
            return;
        }
        //var notif = new Gson().toJson(Map.of("message", notification.toString()));
        var load = new LoadGame(new ChessGame());
        //var notif = new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME);
        var notif = new Notification(load.toString());
        connections.broadcast1(authToken, load);
        connections.broadcast(authToken, notif);
    }

    private void enter(String visitorName, Session session) throws IOException {
        connections.add(visitorName, session);
        var message = String.format("%s is in the shop", visitorName);
        //var notification = new UserGameCommand.CommandType(Notification.Type.ARRIVAL, message);
        //connections.broadcast(visitorName, notification);
    }

    private void exit(String visitorName) throws IOException {
    }
}
