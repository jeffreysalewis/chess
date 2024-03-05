package servicesTests;

import org.junit.jupiter.api.*;
import service.*;

public class CreateGameServiceTest {
    @Test
    @DisplayName("CreateGameService test ;p")
    public void createGameServiceTest() throws Exception {
        var a = new RegistrationService("usernombre", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var d = c.create(auth);
        System.out.println(d);
    }
}
