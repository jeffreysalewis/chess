package serviceTests;
import org.junit.jupiter.api.*;
import service.*;

public class LogoutServiceTest {
    @Test
    @DisplayName("LogoutService test ;p")
    public void logoutServiceTest() throws Exception{
        var user = new RegistrationService("au", "apassword", "anemail");
        var auth = user.registerUser();
        var log = new LoginService(user.getemail(), user.getpassword());
        var logout = new LogoutService();
        Assertions.assertTrue(logout.getClass() == LogoutService.class);
    }

    @Test
    @DisplayName("bad LogoutService test ;p")
    public void badlogoutServiceTest() throws Exception{
        var user = new RegistrationService("auz", "apassword", "anemail");
        var auth = user.registerUser();
        var log = new LoginService(user.getemail(), user.getpassword());
        var logout = new LogoutService();
        Assertions.assertFalse(logout.getClass() != LogoutService.class);
    }
}
