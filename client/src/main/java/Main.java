import serverfac.ServerFacade;
import ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessClient.main(args);
        ServerFacade servador = new ServerFacade();
    }
}