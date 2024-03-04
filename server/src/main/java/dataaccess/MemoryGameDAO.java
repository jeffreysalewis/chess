package dataaccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import chess.*;
import com.google.gson.Gson;

public class MemoryGameDAO implements GameDAO {
    private static int uuid = 0;
    private String authtoken;
    private static HashMap<Integer, String[]> gamedata = new HashMap<>();
    public MemoryGameDAO() {
        MemoryGameDAO.uuid += 1;
    }

    public int nueva(String name) {
        var gameid = MemoryGameDAO.uuid;
        var game = new ChessGame();
        game.getBoard().resetBoard();
        game.setBoard(game.getBoard());
        var gamejson = new Gson().toJson(game);
        System.out.println(gamejson);
        MemoryGameDAO.gamedata.put(gameid, new String[]{Integer.toString(gameid), "", "", name, gamejson});
        return gameid;
    }

    public void join(String color, String id, String username) {
        return;
    }

    public Collection<String[]> list() {
        var gamelist = MemoryGameDAO.gamedata.values();
        return MemoryGameDAO.gamedata.values();
    }

    public static void clear() {
        MemoryGameDAO.uuid = 0;
        MemoryGameDAO.gamedata = new HashMap<>();
    }
}
