package ui;

import serverfac.ServerFacade;

import java.util.Scanner;
import java.util.*;

public class ChessClient {
    public static void main(String[] args) {
        var abierta = true;var stage = "pre";
        System.out.println("Type \"help\" for list of commands\n ");
        ServerFacade servador = new ServerFacade();
        var authtoken = "";
        while (abierta) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            var cmds = line.split(" ");
            String result = "";var us = "";var pw = "";var em = "";
            if(stage.equals("pre")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("register <username> <password> <email>\nlogin <username> <password>\nquit\nhelp");break;
                    case "quit":
                        abierta = false;break;
                    case "login":
                        try {
                            us = cmds[1];pw = cmds[2];
                            authtoken = servador.login(us, pw);
                            System.out.println("Successfully logged in!");
                            System.out.println("Type \"help\" for list of new commands");
                            stage = "post";
                        } catch (Exception e) {
                            System.out.println("Error: could not login: " + e.getMessage());
                        }break;
                    case "register":
                        try {
                            us = cmds[1];pw = cmds[2];em = cmds[3];
                            authtoken = servador.register(us, pw, em);
                            //authtoken = servador.login(us, pw);
                            System.out.println("Registration complete!\nType \"help\" for list of new commands");
                            stage = "post";
                        } catch (Exception e) {System.out.println("Error: could not register: " + e.getMessage());}break;
                }
            }
            int id;var bw = "";
            if(stage.equals("post")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("create <name>\nlist\njoin <id> [WHITE|BLACK|<empty>]\nobserve <id>\nlogout\nquit\nhelp");break;
                    case "quit":
                        abierta = false;break;
                    case "create":
                        try {
                            var name = cmds[1];
                            id = servador.creategame(authtoken, name);
                            System.out.println("Game successfully created!");
                            System.out.println("gameID: " + Integer.toString(id));
                        } catch (Exception e) {System.out.println("Error: could not create game: " + e.getMessage());}break;
                    case "list":
                        try {
                            var lista = servador.listgames(authtoken);
                            for(var al : lista) {System.out.println(al.toString());}
                        } catch (Exception e) {System.out.println("Error: could not list games: " + e.getMessage());}break;
                    case "join":
                        try {
                            id = Integer.parseInt(cmds[1]);
                            if (cmds.length > 2) {bw = cmds[2];}
                            GameplayUI.bw = bw.equals("BLACK");
                            servador.joingame(authtoken, bw, id);
                            stage = "play";
                            GameplayUI.main(args);
                        } catch (Exception e) {System.out.println("Error: could not join game: " + e.getMessage());}break;
                    case "observe":
                        try {
                            id = Integer.parseInt(cmds[1]);
                            servador.joingame(authtoken, "", id);
                            stage = "play";
                            GameplayUI.bw = false;
                            GameplayUI.main(args);
                        } catch (Exception e) {
                            System.out.println("Error: could not observe game: " + e.getMessage());
                        }break;
                    case "logout":
                        try {
                            servador.logout(authtoken);
                            System.out.println("Successfully logged out!");
                        } catch (Exception e) {System.out.println("Error: could not logout: " + e.getMessage());}
                        stage = "pre";break;
                }
            }
            if(stage.equals("play")) {
                switch (cmds[0].toLowerCase()) {
                    case "help":
                        System.out.println("quit\nhelp");break;
                    case "quit":
                        abierta = false;break;
                }}}}
}
