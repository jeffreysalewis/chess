package server;
import com.google.gson.Gson;
import dataaccess.*;
import spark.*;
import exception.*;
import service.*;

import java.util.*;

public class Server {
    //private MemoryUserDAO userdat = new MemoryUserDAO();
    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::register);
        Spark.post("/session", this::login);
        Spark.delete("/session", this::logout);
        Spark.get("/game", this::listgames);
        Spark.post("/game", this::creategame);
        Spark.put("/game", this::joingame);
        Spark.delete("/db", this::clear);

        Spark.exception(ResponseException.class, this::exceptionHandler);
        Spark.awaitInitialization();
        return Spark.port();
    }

    public int port() {
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
    }

    private void exceptionHandler(ResponseException ex, Request req, Response res) {
        res.status(ex.StatusCode());
    }

    private Object register(Request req, Response res) throws ResponseException {
        var user = new Gson().fromJson(req.body(), RegistrationService.class);
        res.type("application/json");
        res.status(200);
        var temp = user.registerUser();
        return new Gson().toJson(Map.of("authtoken", temp));
    }

    private Object login(Request req, Response res) throws ResponseException {
        var session = new Gson().fromJson(req.body(), LoginService.class);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(null);
    }

    private Object logout(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var session = new LogoutService();
        session.logout(auth);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(null);
    }

    private Object listgames(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var games = new ListGamesService();
        var gameslist = games.getgames(auth);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(Map.of("games", gameslist));
    }

    private Object creategame(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var game = new Gson().fromJson(req.body(), CreateGameService.class);
        var id = game.create(auth);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(Map.of("id",id));
    }

    private Object joingame(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var game = new Gson().fromJson(req.body(), JoinGameService.class);
        game.join(auth);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(null);
    }

    private Object clear(Request req, Response res) throws ResponseException {
        var db = new Clear();
        db.clear();
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(null);
    }
}
