package dataaccess;

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

    public Map<Integer, String[]> list() {
        return MemoryGameDAO.gamedata;
    }
}
