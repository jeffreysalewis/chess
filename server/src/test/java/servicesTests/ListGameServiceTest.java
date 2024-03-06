package servicesTests;
import org.junit.jupiter.api.*;
import service.Clear;
import service.*;

import java.util.Map;

public class ListGameServiceTest {
    @Test
    @DisplayName("ListGameService test ;p")
    public void listGameServiceTest() throws Exception{
        var a = new RegistrationService("usernombr", "pass123", "email@example.com");
        var auth = a.registerUser();
        var c = new CreateGameService("gamename");
        var gameid = c.create(auth);
        var l = new ListGamesService();
        var lis = l.getgames(auth);
        Assertions.assertNotNull(lis);
    }

    @Test
    @DisplayName("bad ListGameService test ;p")
    public void badlistGameServiceTest() throws Exception{
        var a = new RegistrationService("usernombre", "pass123", "email@example.com");
        var auth = a.registerUser();
        var l = new ListGamesService();
        Map<String, String>[] lis = l.getgames(auth);
        Assertions.assertTrue(lis.length == 0);
    }
}
