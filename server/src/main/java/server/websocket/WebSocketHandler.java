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
import java.util.HashMap;
import java.util.Map;


@WebSocket
public class WebSocketHandler {

    //private final ConnectionManager connections = new ConnectionManager();
    private final HashMap<Integer, ConnectionManager> connections = new HashMap<>();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        UserGameCommand msg = new Gson().fromJson(message, UserGameCommand.class);
        switch (msg.getCommandType()) {
            case JOIN_PLAYER -> joinplayer(msg.getAuthString(), session, message);
            case JOIN_OBSERVER -> joinobserver(msg.getAuthString(), session, message);
            case MAKE_MOVE -> makemove(msg.getAuthString(), session, message);
            case LEAVE -> leave(msg.getAuthString(), session, message);
            case RESIGN -> resign(msg.getAuthString(), session, message);
        }
    }

    private void joinplayer(String authToken, Session session, String message) throws IOException {
        var notification = new Gson().fromJson(message, JoinPlayer.class);
        if(connections.get(notification.getGameID()) == null) {
            connections.put(notification.getGameID(), new ConnectionManager());
        }
        connections.get(notification.getGameID()).add(authToken, session);
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
            connections.get(notification.getGameID()).broadcast1(authToken, er);
            System.out.println(r.getMessage());
            return;
        }
        //var notif = new Gson().toJson(Map.of("message", notification.toString()));
        var load = new LoadGame(new ChessGame());
        //var notif = new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME);
        var notif = new Notification(load.toString());
        connections.get(notification.getGameID()).broadcast1(authToken, load);
        connections.get(notification.getGameID()).broadcast(authToken, notif);
    }

    private void joinobserver(String authToken, Session session, String message) throws IOException {
        String username = "";
        var notification = new Gson().fromJson(message, JoinPlayer.class);
        if(connections.get(notification.getGameID()) == null) {
            connections.put(notification.getGameID(), new ConnectionManager());
        }
        connections.get(notification.getGameID()).add(authToken, session);
        try {
            var gdao = new SqlGameDAO();
            var juego = gdao.getGame(notification.getGameID());
            var jnum = juego.get("gameID");
            username = SqlAuthDAO.getUserfromAuth(authToken);
            if(jnum == null) {
                throw new ResponseException(500, "Error: error");
            }
            var iden = Integer.parseInt(juego.get("gameID"));
            if(iden != notification.getGameID()) {
                throw new ResponseException(501, "Error: error yus");
            }
        } catch (ResponseException r) {
            ServerMessage er = new Error("error: error");
            connections.get(notification.getGameID()).broadcast1(authToken, er);
            //System.out.println(r.getMessage());
            return;
        }
        var load = new LoadGame(new ChessGame());
        var notif = new Notification(load.toString());
        connections.get(notification.getGameID()).broadcast1(authToken, load);
        connections.get(notification.getGameID()).broadcast(authToken, notif);
    }

    private void makemove(String authToken, Session session, String message) throws IOException{
        var notification = new Gson().fromJson(message, MakeMove.class);
        try{
            var gdao = new SqlGameDAO();
            //var c = new ChessGame();
            var g = gdao.getGame(notification.getGameID());
            var gjson = g.get("gamejson");
            var jueg = new Gson().fromJson(gjson, ChessGame.class);
            if(jueg == null) {
                var jue = new Gson().fromJson(gjson, JuegaJson.class);
                jueg = new ChessGame();
                jueg.setBoard(jue.getGameboard());
                jueg.setTeamTurn(jue.getTurncolor());
            }
            jueg.makeMove(notification.getMove());
            var load = new LoadGame(jueg);
            var notif = new Notification(load.toString());
            connections.get(notification.getGameID()).broadcast1(authToken, load);
            connections.get(notification.getGameID()).broadcast(authToken, load);
            connections.get(notification.getGameID()).broadcast(authToken, notif);
        } catch (Exception e) {
            ServerMessage er = new Error("error: cannot make move");
            connections.get(notification.getGameID()).broadcast1(authToken, er);
            return;
        }
    }

    private void leave(String authToken, Session session, String message) throws IOException{
        var notification = new Gson().fromJson(message, Leave.class);
        try{
            var notif = new Notification(notification.toString());
            connections.get(notification.getGameID()).broadcast1(authToken, notif);
            connections.get(notification.getGameID()).broadcast(authToken, notif);
            connections.get(notification.getGameID()).remove(authToken);
        } catch (Exception e) {
            ServerMessage er = new Error("error: cannot make move");
            connections.get(notification.getGameID()).broadcast1(authToken, er);
            return;
        }
    }

    private void resign(String authToken, Session session, String message) throws IOException{
        var notification = new Gson().fromJson(message, Resign.class);
        try{
            var username = SqlAuthDAO.getUserfromAuth(authToken);
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
            if(!username.equals(juego.get("whiteUsername")) && !username.equals(juego.get("blackUsername"))) {
                throw new ResponseException(500, "Error: observer cannot resign");
            } else if(username.equals(juego.get("whiteUsername"))) {

            }
            var notif = new Notification(notification.toString());
            connections.get(notification.getGameID()).broadcast1(authToken, notif);
            connections.get(notification.getGameID()).broadcast(authToken, notif);
        } catch (Exception e) {
            ServerMessage er = new Error("error: user cannot resign");
            connections.get(notification.getGameID()).broadcast1(authToken, er);
            return;
        }
    }
}
