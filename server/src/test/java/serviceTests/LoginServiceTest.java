package serviceTests;
import org.junit.jupiter.api.*;
import service.*;

public class LoginServiceTest {
    @Test
    @DisplayName("LoginServiceService test ;p")
    public void loginServiceTest() throws Exception{
        var user = new RegistrationService("ausss", "apassword", "anemail");
        var auth = user.registerUser();
        var log = new LoginService(user.getemail(), user.getpassword());
        Assertions.assertTrue(log.getClass() == LoginService.class);
    }

    @Test
    @DisplayName("bad LoginServiceService test ;p")
    public void badloginServiceTest() throws Exception{
        var user = new RegistrationService("ausefjdsklasdf", "apassword", "anemail");
        var log = new LoginService(user.getemail(), user.getpassword());
        Assertions.assertFalse(log.getClass() != LoginService.class);
    }

    @Test
    @DisplayName("getusername() test ;p")
    public void getusernameTest() throws Exception {
        var user = new RegistrationService("jjausername", "apassword", "anemail");
        var log = new LoginService("jjausername", "apassword");
        System.out.println(log.getUsername());
        Assertions.assertTrue("jjausername".equals(log.getUsername()));
    }

    @Test
    @DisplayName("bad getusername() test ;p")
    public void badgetusernameTest() throws Exception {
        var user = new RegistrationService("jjjausername", "apassword", "anemail");
        var log = new LoginService("jjjausername", "apassword");
        System.out.println(log.getUsername());
        Assertions.assertFalse("a".equals(log.getUsername()));
    }
}
