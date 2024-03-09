package dataaccessTests;

import dataAccess.SqlAuthDAO;
import dataAccess.SqlUserDAO;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthDAOTest {
    @Test
    @DisplayName("createAuth() test >:D")
    public void createAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            Assertions.assertNotNull(outauth);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad createAuth() test >:D")
    public void badcreateAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "";
            var outauth = newauthdata.createAuth(randuser);
            Assertions.assertNotNull(outauth);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("getUser() test >:D")
    public void getUserTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            var outp = newauthdata.getUser(randuser);
            Assertions.assertEquals(outp[0], randuser);
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
            var newauthdata = new SqlAuthDAO();
            var randuser = "jnvajkb4ionjkv";
            var outauth = newauthdata.createAuth(randuser);
            var outp = newauthdata.getUser(randuser);
            Assertions.assertNotEquals(outp[1], randuser);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("authorize() test >:D")
    public void authorizeTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            Assertions.assertTrue(SqlAuthDAO.authorize(outauth));
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad authorize() test >:D")
    public void badauthorizeTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var defauth = "definitelyanauthtoken";
            try {
                Assertions.assertFalse(SqlAuthDAO.authorize(defauth));
            }catch (Exception ex) {
                Assertions.assertFalse(false);
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
    @DisplayName("getUserfromAuth() test >:D")
    public void getUserfromAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            var outp = SqlAuthDAO.getUserfromAuth(outauth);
            Assertions.assertEquals(outp, randuser);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad getUserfromAuth() test >:D")
    public void badgetUserfromAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "jnvajkb4ionjkv";
            var outauth = newauthdata.createAuth(randuser);
            var outp = SqlAuthDAO.getUserfromAuth(outauth);
            Assertions.assertNotEquals(outp, outauth);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("deleteAuth() test >:D")
    public void deleteAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            SqlAuthDAO.deleteAuth(outauth);
            try {
                var outp = SqlAuthDAO.getUserfromAuth(outauth);
                Assertions.fail();
            } catch (Exception exc) {
                Assertions.assertTrue(true);
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
    @DisplayName("bad deleteAuth() test >:D")
    public void baddeleteAuthTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            SqlAuthDAO.deleteAuth(outauth);
            try {
                var outp = SqlAuthDAO.getUserfromAuth(outauth);
                Assertions.fail();
            } catch (Exception exc) {
                Assertions.assertTrue(true);
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
    @DisplayName("clear() test >:D")
    public void clearTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            SqlAuthDAO.clear();
            try{
                newauthdata.getUser(randuser);
                Assertions.fail();
            } catch (Exception e) {
                Assertions.assertTrue(true);
            }
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.fail();
        }catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad clear() test >:D")
    public void badclearTest() {
        try {
            var newauthdata = new SqlAuthDAO();
            var randuser = "eajnvava4ionv";
            var outauth = newauthdata.createAuth(randuser);
            SqlAuthDAO.clear();
            try{
                newauthdata.getUser(randuser);
                Assertions.assertTrue(false);
            } catch (Exception e) {
                Assertions.assertTrue(true);
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
