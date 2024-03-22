package serverfac;
import com.google.gson.Gson;
import jsonObjClasses.*;

import java.util.*;
import java.io.*;
import java.net.*;

public class ServerFacade {

    public String register(String username, String password, String email) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("username", username, "password", password, "email", email);
        var jsonBody = new Gson().toJson(body);
        var retjson = httpcon.run("user", "POST", null, jsonBody);
        var auth = new Gson().fromJson(retjson, userAuth.class);
        return auth.getAuthToken();
    }

    public String login(String username, String password) throws Exception{
        var authToken = "";
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("username", username, "password", password);
        var jsonBody = new Gson().toJson(body);
        var retjson = httpcon.run("session", "POST", null, jsonBody);
        var auth = new Gson().fromJson(retjson, userAuth.class);
        return auth.getAuthToken();
    }

    public void logout(String authToken) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        httpcon.run("session", "DELETE", authToken, null);
    }

    public Map<String, String>[] listgames(String authToken) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var retjson = httpcon.run("game", "GET", authToken, null);
        var lista = new Gson().fromJson(retjson, listaDeJuegos.class);
        return lista.getGames();
    }

    public int creategame(String authToken, String gameName) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("gameName", gameName);
        var jsonBody = new Gson().toJson(body);
        var retjson = httpcon.run("game", "POST", authToken, jsonBody);
        var id = new Gson().fromJson(retjson, idDeJuegos.class);
        return id.getGameID();
    }

    public void joingame(String authToken, String playerColor, int gameID) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("playerColor", playerColor, "gameID", gameID);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("game", "PUT", authToken, jsonBody);
    }

    public void clear() throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        httpcon.run("db", "DELETE", null, null);
    }
}
