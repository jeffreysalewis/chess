package dataaccessTests;

import dataAccess.SqlUserDAO;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.Clear;

public class UserDAOTest {
    @Test
    @DisplayName("getUser() test >:D")
    public void getUserTest() {
        var newuserdata = new SqlUserDAO();
        try {
            newuserdata.createUser("woahausername", "shhhpassword", "realemail@email.com");
        } catch (ResponseException r) {
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad getUser() test >:D")
    public void badgetUserTest() {
    }

    @Test
    @DisplayName("createUser() test >:D")
    public void createUserTest() {
        var newuserdata = new SqlUserDAO();
        try {
            newuserdata.createUser("woahausername", "shhhpassword", "realemail@email.com");
            newuserdata.getUser("woadausername");
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad createUser() test >:D")
    public void badcreateUserTest() {
    }
}
