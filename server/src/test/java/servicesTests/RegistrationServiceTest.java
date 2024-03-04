package servicesTests;

import exception.ResponseException;
import org.junit.jupiter.api.*;
import service.RegistrationService;

public class RegistrationServiceTest {
    @Test
    @DisplayName("RegistrationService test ;p")
    public void registrationServiceTest() throws Exception {
        var user = new RegistrationService("ausername", "apassword", "anemail");
        System.out.println(user.registerUser());
        Assertions.assertTrue("ausername".equals(user.getusername()));
        Assertions.assertTrue("apassword".equals(user.getpassword()));
        Assertions.assertTrue("anemail".equals(user.getemail()));
    }
}
