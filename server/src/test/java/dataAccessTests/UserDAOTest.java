package dataAccessTests;

import dataAccess.SqlUserDAO;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDAOTest {
    @Test
    @DisplayName("createUser() test >:D")
    public void createUserTest() {
        try {
            var newuserdata = new SqlUserDAO();
            String[] randuser = {"woahausername", "shhhpassword", "realemail@email.com"};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("bad createUser() test >:D")
    public void badcreateUserTest() {
        try {
            var newuserdata = new SqlUserDAO();
            String[] randuser = {null, null, null};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
            Assertions.fail();
        } catch (Exception r) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("getUser() test >:D")
    public void getUserTest() {
        try {
            var newuserdata = new SqlUserDAO();
            String[] randuser = {"woahausername2", "shhhpassword2", "realemail2@email.com"};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
            var out = newuserdata.getUser(randuser[0]);
            for (var a = 0; a< randuser.length; a++) {
                Assertions.assertEquals(randuser[a], out[a]);
            }
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad getUser() test >:D")
    public void badgetUserTest() {
        try {
            var newuserdata = new SqlUserDAO();
            String[] randuser = {"woahausername5", "shhhpassword5", "realemail5@email.com"};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
            var out = newuserdata.getUser("randuser[0]");
            for (var a = 0; a< randuser.length; a++) {
                Assertions.assertNotEquals(randuser[a], out[a]);
            }
        } catch (ResponseException r) {
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("clear() test >:D")
    public void clearTest() {
        try {
            var newuserdata = new SqlUserDAO();
            String[] randuser = {"woahausername3", "shhhpassword3", "realemail3@email.com"};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
            SqlUserDAO.clear();
            try{
                newuserdata.getUser(randuser[0]);
                Assertions.fail();
            } catch (Exception e) {
                Assertions.assertTrue(true);
            }
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad clear() test >:D")
    public void badclearTest() {
        try {
            var newuserdata = new SqlUserDAO();
            SqlUserDAO.clear();
            String[] randuser = {"woahausername6", "shhhpassword6", "realemail6@email.com"};
            newuserdata.createUser(randuser[0], randuser[1], randuser[2]);
            try{
                newuserdata.getUser(randuser[0]);
                Assertions.assertTrue(true);
            } catch (Exception e) {
                Assertions.fail();
            }
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.fail();
        }catch (Exception e) {
            Assertions.fail();
        }
    }
}
