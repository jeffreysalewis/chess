package servicesTests;
import exception.ResponseException;
import org.junit.jupiter.api.*;
import service.*;

public class JoinGameServiceTest {
    @Test
    @DisplayName("JoinGameService test ;p")
    public void joinGameServiceTest() throws Exception {
        var cl = new Clear();
        cl.clear();
        var a = new RegistrationService("usern", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var gameid = c.create(auth);
        var j = new JoinGameService("BLACK", gameid);
        j.join(auth);
        Assertions.assertTrue(j.getClass() == JoinGameService.class);
    }

    @Test
    @DisplayName("bad JoinGameService test ;p")
    public void badjoinGameServiceTest() throws Exception {
        var cl = new Clear();
        cl.clear();
        var a = new RegistrationService("usernom", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var gameid = c.create(auth);
        var j = new JoinGameService("BLACK", gameid);
        j.join(auth);
        Assertions.assertFalse(j.getClass() != JoinGameService.class);
    }
}
