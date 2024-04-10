package jsonObjClasses;

import java.util.Map;

public class ListaDeJuegos {
    private Map<String, String>[] games;

    public ListaDeJuegos(Map<String, String>[] games) {
        this.games = games;
    }

    public Map<String, String>[] getGames() {
        return this.games;
    }
}
