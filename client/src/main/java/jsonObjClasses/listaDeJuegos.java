package jsonObjClasses;

import java.util.Map;

public class listaDeJuegos {
    private Map<String, String>[] games;

    public listaDeJuegos(Map<String, String>[] games) {
        this.games = games;
    }

    public Map<String, String>[] getGames() {
        return this.games;
    }
}
