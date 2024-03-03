package servicesTests;
import org.junit.jupiter.api.*;
import service.*;

public class LoginServiceTest {
    @Test
    @DisplayName("LoginServiceService test ;p")
    public void loginServiceTest() {
        var user = new RegistrationService("ausername", "apassword", "anemail");
        var log = new LoginService(user.getemail(), user.getpassword());
    }
}
