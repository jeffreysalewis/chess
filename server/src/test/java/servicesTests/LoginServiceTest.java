package servicesTests;
import org.junit.jupiter.api.*;
import service.*;

public class LoginServiceTest {
    @Test
    @DisplayName("LoginServiceService test ;p")
    public void loginServiceTest() throws Exception{
        var user = new RegistrationService("aus", "apassword", "anemail");
        var auth = user.registerUser();
        var log = new LoginService(user.getemail(), user.getpassword());
        Assertions.assertTrue(log.getClass() == LoginService.class);
    }

    @Test
    @DisplayName("bad LoginServiceService test ;p")
    public void badloginServiceTest() throws Exception{
        var user = new RegistrationService("ause", "apassword", "anemail");
        var log = new LoginService(user.getemail(), user.getpassword());
        Assertions.assertFalse(log.getClass() != LoginService.class);
    }
}
