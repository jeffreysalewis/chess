package servicesTests;

import exception.ResponseException;
import org.junit.jupiter.api.*;
import service.RegistrationService;

public class RegistrationServiceTest {
    @Test
    @DisplayName("RegistrationService test ;p")
    public void registrationServiceTest() throws Exception {
        var user = new RegistrationService("aaaausername", "apassword", "anemail");
        System.out.println(user.registerUser());
        Assertions.assertTrue("aaaausername".equals(user.getusername()));
        Assertions.assertTrue("apassword".equals(user.getpassword()));
        Assertions.assertTrue("anemail".equals(user.getemail()));
    }

    @Test
    @DisplayName("bad RegistrationService test ;p")
    public void badregistrationServiceTest() throws Exception {
        var user = new RegistrationService("acusername", "ausername", "ausername");
        Assertions.assertNotNull(user.registerUser());
    }

    @Test
    @DisplayName("getusername() test ;p")
    public void getusernameTest() throws Exception {
        var user = new RegistrationService("aausername", "apassword", "anemail");
        System.out.println(user.getusername());
        Assertions.assertTrue("aausername".equals(user.getusername()));
    }

    @Test
    @DisplayName("badgetusername() test ;p")
    public void badgetusernameTest() throws Exception {
        var user = new RegistrationService("ausername", "apassword", "anemail");
        System.out.println(user.getusername());
        Assertions.assertFalse("ano".equals(user.getusername()));
    }

    @Test
    @DisplayName("getpasswrod() test ;p")
    public void getpasswordTest() throws Exception {
        var user = new RegistrationService("ausername", "apassword", "anemail");
        System.out.println(user.getpassword());
        Assertions.assertTrue("apassword".equals(user.getpassword()));
    }

    @Test
    @DisplayName("badgetpassword() test ;p")
    public void badgetpasswordTest() throws Exception {
        var user = new RegistrationService("aasdfdausername", "apassword", "anemail");
        System.out.println(user.getpassword());
        Assertions.assertFalse("ap".equals(user.getpassword()));
    }

    @Test
    @DisplayName("getemail() test ;p")
    public void getemailTest() throws Exception {
        var user = new RegistrationService("accccusername", "apassword", "anemail");
        System.out.println(user.getemail());
        Assertions.assertTrue("anemail".equals(user.getemail()));
    }

    @Test
    @DisplayName("badgetemail() test ;p")
    public void badgetemailTest() throws Exception {
        var user = new RegistrationService("anofdafdusername", "apassword", "anemail");
        System.out.println(user.getemail());
        Assertions.assertFalse("ano".equals(user.getemail()));
    }
}
