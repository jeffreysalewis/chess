package server;
import com.google.gson.Gson;
import spark.*;
import exception.*;
import service.*;

public class Server {
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
        System.out.println(req.body());
        System.out.println(user);
        //System.out.println(user.getauthtoken());
        //var authtoken = user.getauthtoken();
        res.status(200);
        return new Gson().toJson(user.getauthtoken());
    }

    private Object login(Request req, Response res) throws ResponseException {
        var session = new Gson().fromJson(req.body(), LoginService.class);
        res.type("application/json");
        res.status(200);
        return new Gson().toJson(0);
    }

    private Object logout(Request req, Response res) throws ResponseException {
//        var user = new Gson().fromJson(req.body(), LogoutService.class);
//        res.type("application/json");
//        var authtoken = user.getauthtoken();
        return "";
    }

    private Object listgames(Request req, Response res) throws ResponseException {
//        var games = new Gson().fromJson(req.body(), ListGameService.class);
//        res.type("application/json");
//        var gameslist = games.getgames();
        return "";
    }

    private Object creategame(Request req, Response res) throws ResponseException {
//        var user = new Gson().fromJson(req.body(), CreateGameService.class);
//        res.type("application/json");
//        var authtoken = user.getauthtoken();
        return "";
    }

    private Object joingame(Request req, Response res) throws ResponseException {
//        var user = new Gson().fromJson(req.body(), JoinGameService.class);
//        res.type("application/json");
//        var authtoken = user.getauthtoken();
        return "";
    }

    private Object clear(Request req, Response res) throws ResponseException {
//        var user = new Gson().fromJson(req.body(), Clear.class);
//        res.type("application/json");
//        var authtoken = user.getauthtoken();
        return "";
    }
}
