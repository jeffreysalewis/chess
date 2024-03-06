package dataaccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import chess.*;
import com.google.gson.Gson;
import exception.ResponseException;

public class MemoryGameDAO implements GameDAO {
    private static int uuid = 1;
    private String authtoken;
    private static HashMap<Integer, String[]> gamedata = new HashMap<>();
    public MemoryGameDAO() {
    }

    public int nueva(String name) {
        var gameid = MemoryGameDAO.uuid;
        var game = new ChessGame();
        game.getBoard().resetBoard();
        game.setBoard(game.getBoard());
        var gamejson = new Gson().toJson(game);
        MemoryGameDAO.gamedata.put(gameid, new String[]{Integer.toString(gameid), null, null, name, gamejson});
        MemoryGameDAO.uuid++;
        return gameid;
    }

    public void join(String color, int id, String username) throws ResponseException{
        if(MemoryGameDAO.gamedata.containsKey(id)) {
            var wantgame = MemoryGameDAO.gamedata.get(id);

            if("WHITE".equals(color)) {
                if(wantgame[1] != null) {
                    throw new ResponseException(403, "Error: already taken");
                }
                wantgame[1] = username;
                MemoryGameDAO.gamedata.put(id, wantgame);
            } else if("BLACK".equals(color)) {
                if(wantgame[2] != null) {
                    throw new ResponseException(403, "Error: already taken");
                }
                wantgame[2] = username;
                MemoryGameDAO.gamedata.put(id, wantgame);
            }

        } else {
            throw new ResponseException(400, "Error: bad request");
        }
    }

    public Map<String, String>[] list() {
        var gamelist = MemoryGameDAO.gamedata.values();
        var bettergamelist = new HashMap[MemoryGameDAO.gamedata.size()];
        int a = 0;
        for (var onegame:gamelist) {
            bettergamelist[a] = new HashMap<String, String>();
            bettergamelist[a].put("gameID", onegame[0]);
            bettergamelist[a].put("whiteUsername", onegame[1]);
            bettergamelist[a].put("blackUsername", onegame[2]);
            bettergamelist[a].put("gameName", onegame[3]);
            a++;
        }
        return bettergamelist;
    }

    public static void clear() {
        MemoryGameDAO.gamedata.clear();
    }
}
