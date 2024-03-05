package servicesTests;
import exception.ResponseException;
import org.junit.jupiter.api.*;
import service.*;

public class JoinGameServiceTest {
    @Test
    @DisplayName("JoinGameService test ;p")
    public void joinGameServiceTest() throws Exception {
        var a = new RegistrationService("usernombre", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var gameid = c.create(auth);
        var j = new JoinGameService("BLACK", gameid);
        j.join(auth);
    }
}
