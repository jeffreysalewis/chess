import serverfac.ServerFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var abierta = true;
        var stage = "pre";
        System.out.println("Type help for list of commands\n ");
        ServerFacade servador = new ServerFacade();
        while (abierta) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            var cmds = line.split(" ");

            String result = "";
            var us = "";
            var pw = "";
            var em = "";
            if(stage.equals("pre")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("register <username> <password> <email>");
                        System.out.println("login <username> <password>");
                        System.out.println("quit");
                        System.out.println("help");
                        break;
                    case "quit":
                        abierta = false;
                        break;
                    case "login":
                        stage = "post";
                        us = cmds[1];
                        pw = cmds[2];
                        break;
                    case "register":
                        stage = "post";
                        us = cmds[1];
                        pw = cmds[2];
                        em = cmds[3];
                        try {
                            servador.register(us, pw, em);
                        } catch (Exception e) {
                            System.out.println("Error: could not register: " + e.getMessage());
                        }
                        break;
                }
            }
            var id = "";
            var bw = "";
            if(stage.equals("post")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("create <name>");
                        System.out.println("list");
                        System.out.println("join <id> [WHITE|BLACK|<empty>]");
                        System.out.println("observe <id>");
                        System.out.println("logout");
                        System.out.println("quit");
                        System.out.println("help");
                        break;
                    case "quit":
                        abierta = false;
                        break;
                    case "create":
                        id = cmds[1];
                        break;
                    case "join":
                        stage = "play";
                        id = cmds[1];
                        if(cmds.length>2) {
                            bw = cmds[2];
                        }
                        break;
                    case "observe":
                        stage = "play";
                        id = cmds[1];
                        break;
                    case "logout":
                        stage = "pre";
                        break;
                }
            }
            if(stage.equals("play")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("quit");
                        System.out.println("help");
                        break;
                    case "quit":
                        abierta = false;
                        break;
                }
            }
        }
        //var out =new PrintStream(System.out, true, StandardCharsets.UTF_8);var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);System.out.println("♕ 240 Chess Client: " + piece);
    }
}