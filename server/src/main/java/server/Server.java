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
        res.type("application/json");
        res.status(200);
        try {
            var user = new Gson().fromJson(req.body(), RegistrationService.class);
            var temp = user.registerUser();
            var logout = new LogoutService();
            logout.logout(temp);
            var log = new LoginService(user.getusername(), user.getpassword());
            temp = log.login()[1];
            res.status(200);
            return new Gson().toJson(Map.ofEntries(Map.entry("username", user.getusername()), Map.entry("authToken", temp)));
        } catch (ResponseException r){
            res.status(r.StatusCode());
            return new Gson().toJson(Map.of("message", r.getMessage()));
        }
    }

    private Object login(Request req, Response res) throws ResponseException {
        var session = new Gson().fromJson(req.body(), LoginService.class);
        res.type("application/json");
        res.status(200);
        //res.status(401);
        try {
            var log = session.login();
            if(log != null && log.length >1) {
                res.status(200);
                return new Gson().toJson(Map.ofEntries(Map.entry("username", log[0]), Map.entry("authToken", log[1])));
            } else {
                //res.status(401);
                throw new ResponseException(401, "Error: unauthorized");
            }
        } catch (ResponseException e) {
            res.status(e.StatusCode());
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }

    private Object logout(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var session = new LogoutService();
        res.type("application/json");
        res.status(200);
        try {
            session.logout(auth);
            res.status(200);
            return new Gson().toJson(null);
        } catch (ResponseException r) {
            res.status(r.StatusCode());
            return new Gson().toJson(Map.of("message", r.getMessage()));
        }
    }

    private Object listgames(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");

        res.type("application/json");
        try {
            var games = new ListGamesService();
            var gameslist = games.getgames(auth);
            var gameslistjson = new Gson().toJson(gameslist);
            res.status(200);
            return new Gson().toJson(Map.of("games", gameslist));
        } catch(ResponseException r) {
//            if (r.StatusCode() == 600) {
//                res.status(600);
//                return new Gson().toJson(Map.of("message", "Error: gameslist is null"));
//            }
            res.status(r.StatusCode());
            return new Gson().toJson(Map.of("message", r.getMessage()));
        }
    }

    private Object creategame(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var game = new Gson().fromJson(req.body(), CreateGameService.class);
        res.type("application/json");
        res.status(200);
        try {
            var id = game.create(auth);
            res.status(200);
            return new Gson().toJson(Map.of("gameID", id));
        } catch (ResponseException r) {
            res.status(r.StatusCode());
            return new Gson().toJson(Map.of("message", r.getMessage()));
//        } catch (Exception e) {
//            res.status(400);
//            return new Gson().toJson(Map.of("message", "Error: bad request"));
        }
    }

    private Object joingame(Request req, Response res) throws ResponseException {
        var auth = req.headers("authorization");
        var game = new Gson().fromJson(req.body(), JoinGameService.class);
        System.out.println(req.body());
        res.status(200);
        res.type("application/json");
        try {
            game.join(auth);
            res.status(200);
            return new Gson().toJson(null);
        } catch (ResponseException r) {
            res.status(r.StatusCode());
            return new Gson().toJson(Map.of("message", r.getMessage()));
            //res.status(401);
            //return new Gson().toJson(Map.of("message", "Error: unauthorized"));
//        } catch (Exception e) {
//            res.status(400);
//            return new Gson().toJson(Map.of("message", "Error: bad request"));
        }
    }

    private Object clear(Request req, Response res) throws ResponseException {
        var db = new Clear();
        db.clear();
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(null);
    }
}
