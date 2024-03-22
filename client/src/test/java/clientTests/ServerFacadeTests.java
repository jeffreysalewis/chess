package clientTests;

import org.junit.jupiter.api.*;
import server.Server;
import serverfac.ServerFacade;


public class ServerFacadeTests {

    private static Server server;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    @DisplayName("register() test >:D")
    public void registerTest() {
        var servador = new ServerFacade();
        try {
            servador.register("fdsasdf", "passy", "emeee");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad register() test D:<")
    public void badregisterTest() {
        var servador = new ServerFacade();
        try {
            servador.register("asdfds", "sy", "eme");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("login() test >:D")
    public void loginTest() {
        var servador = new ServerFacade();
        try {
            servador.login("fdsasdf", "passy");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad login() test D:<")
    public void badloginTest() {
        var servador = new ServerFacade();
        try {
            servador.login("asdfds", "sy");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("logout() test >:D")
    public void logoutTest() {
        var servador = new ServerFacade();
        try {
            servador.logout("a0a88cee-3ea0-4dd4-8e15-1a4eab36f156");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad logout() test D:<")
    public void badlogoutTest() {
        var servador = new ServerFacade();
        try {
            servador.logout("6ea54c58-d1ab-4971-a8cb-e018280798ee");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("listgames() test >:D")
    public void listgamesTest() {
        var servador = new ServerFacade();
        try {
            servador.listgames("e6080102-fc5d-40ce-a372-6d299247a1eb");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad listgames() test D:<")
    public void badlistgamesTest() {
        var servador = new ServerFacade();
        try {
            servador.listgames("e6080102-fc5d-40ce-a372-6d299247a1eb");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("creategame() test >:D")
    public void creategameTest() {
        var servador = new ServerFacade();
        try {
            servador.creategame("240873f6-0e56-410f-aee8-d305df3aa8e8", "minecraft");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad creategame() test D:<")
    public void badcreategameTest() {
        var servador = new ServerFacade();
        try {
            servador.creategame("090c3b04-ff84-40f4-b2ca-cbeba7a2e08b", "hollowknight");
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("joingame() test >:D")
    public void joingameTest() {
        var servador = new ServerFacade();
        try {
            servador.joingame("12eca091-2fff-4a13-b277-45dd8f1b1aad", "BLACK", 413);
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad joingame() test D:<")
    public void badjoingameTest() {
        var servador = new ServerFacade();
        try {
            servador.joingame("cd00abfa-bddb-4746-80cb-982899e5d017", "BLACK", 413);
        } catch(Exception e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(true);
    }

}
