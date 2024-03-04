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

    public Map<String, String>[] list() {
        var gamelist = MemoryGameDAO.gamedata.values();
        var bettergamelist = new HashMap[MemoryGameDAO.gamedata.size()];
        int a = 0;
        for (var onegame:gamelist) {
            if(onegame == null || onegame.length < 5) {
                onegame = new String[5];
            }
            if(onegame[0] == null) {
                onegame[0] = "100000000";
            }
            if(onegame[1] == null) {
                onegame[1] = "";
            }
            if(onegame[2] == null) {
                onegame[2] = "";
            }
            if(onegame[3] == null) {
                onegame[3] = "";
            }
            bettergamelist[a] = new HashMap<String, String>();
            bettergamelist[a].put("gameID", onegame[0]);
            bettergamelist[a].put("whiteUsername", onegame[1]);
            bettergamelist[a].put("blackUsername", onegame[2]);
            bettergamelist[a].put("gameName", onegame[3]);
            //bettergamelist[a] = Map.ofEntries(Map.entry("gameID", onegame[0]), Map.entry("whiteUsername", onegame[1]), Map.entry("blackUsername", onegame[2]), Map.entry("gameName", onegame[3]));
            //bettergamelist[a] = new Gson().toJson(Map.ofEntries(Map.entry("gameID", onegame[0]), Map.entry("whiteUsername", onegame[1]), Map.entry("blackUsername", onegame[2]), Map.entry("gameName", onegame[3])));
            a++;
        }
        return bettergamelist;
        //return MemoryGameDAO.gamedata.values();
    }

    public static void clear() {
        MemoryGameDAO.uuid = 0;
        MemoryGameDAO.gamedata = new HashMap<>();
    }
}
