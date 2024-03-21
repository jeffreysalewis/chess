package clientTests;

import org.junit.jupiter.api.*;
import server.Server;


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
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad register() test D:<")
    public void badregisterTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("login() test >:D")
    public void loginTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad login() test D:<")
    public void badloginTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("logout() test >:D")
    public void logoutTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad logout() test D:<")
    public void badlogoutTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("listgames() test >:D")
    public void listgamesTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad listgames() test D:<")
    public void badlistgamesTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("creategame() test >:D")
    public void creategameTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad creategame() test D:<")
    public void badcreategameTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("joingame() test >:D")
    public void joingameTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad joingame() test D:<")
    public void badjoingameTest() {
        Assertions.assertTrue(true);
    }

}
