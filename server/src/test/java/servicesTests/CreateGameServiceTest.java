package servicesTests;

import org.junit.jupiter.api.*;
import service.*;

public class CreateGameServiceTest {
    @Test
    @DisplayName("CreateGameService test ;p")
    public void createGameServiceTest() throws Exception {
        var a = new RegistrationService("usernombrec", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var d = c.create(auth);
        Assertions.assertTrue(d > -1);
    }

    @Test
    @DisplayName("bad CreateGameService test ;p")
    public void badcreateGameServiceTest() throws Exception {
        var a = new RegistrationService("usernombrea", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var d = c.create(auth);
        Assertions.assertFalse(d < 0);
    }
}
