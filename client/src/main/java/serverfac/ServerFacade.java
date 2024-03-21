package serverfac;
import com.google.gson.Gson;
import java.util.*;
import java.io.*;
import java.net.*;

public class ServerFacade {

    public String register(String username, String password, String email) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("username", username, "password", password, "email", email);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("user", "POST", null, jsonBody);
        return "";
    }

    public String login(String username, String password) throws Exception{
        var authToken = "";
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("username", username, "password", password);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("session", "POST", null, jsonBody);
        return "";
    }

    public void logout(String authToken) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("authToken", authToken);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("session", "DELETE", authToken, jsonBody);
    }

    public Map<String, String>[] listgames(String authToken) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        httpcon.run("game", "GET", authToken, null);
        return null;
    }

    public int creategame(String authToken, String gameName) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("gameName", gameName);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("game", "POST", authToken, jsonBody);
        return 0;
    }

    public void joingame(String authToken, String playerColor, int gameID) throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        var body = Map.of("playerColor", playerColor, "gameID", gameID);
        var jsonBody = new Gson().toJson(body);
        httpcon.run("game", "POST", authToken, jsonBody);
    }

    public void clear() throws Exception{
        HttpCommunicator httpcon = new HttpCommunicator();
        httpcon.run("db", "DELETE", null, null);
    }
}
