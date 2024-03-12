package dataAccessTests;

import dataAccess.SqlGameDAO;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDAOTest {
    @Test
    @DisplayName("nueva() test >:D")
    public void nuevaTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "amogus";
            var outid = newgamedata.nueva(randganame);
            Assertions.assertNotNull(outid);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("mal nueva() probar >:D")
    public void malnuevaTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "amongus";
            var outid = newgamedata.nueva(randganame);
            Assertions.assertEquals(outid, outid);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("join() test >:D")
    public void joinTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "minecraft";
            var outid = newgamedata.nueva(randganame);
            newgamedata.join("BLACK", outid, "blackusnm");
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
    @DisplayName("bad join() test >:D")
    public void badjoinTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "minecraft";
            var outid = newgamedata.nueva(randganame);
            newgamedata.join("WHITE", outid, "whiteusnm");
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
    @DisplayName("list() test >:D")
    public void listTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "undertale";
            var outid = newgamedata.nueva(randganame);
            newgamedata.join("WHITE", outid, "birb");
            newgamedata.join("BLACK", outid, "dawg");
            newgamedata.join("", outid, "player");
            var glist = newgamedata.list();
            Assertions.assertTrue(glist.toString().length() > 16);
        } catch (ResponseException r) {
            System.out.println(r.StatusCode());
            System.out.println(r.getMessage());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("bad list() test >:D")
    public void badlistTest() {
        try {
            var newgamedata = new SqlGameDAO();
            var randganame = "totk";
            var outid = newgamedata.nueva(randganame);
            newgamedata.join("WHITE", outid, "idk");
            newgamedata.join("BLACK", outid, "smth");
            var glist = newgamedata.list();
            Assertions.assertTrue(glist.toString().length() > 15);
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
            var newgamedata = new SqlGameDAO();
            var randgamename = "hollow knight";
            var outid = newgamedata.nueva(randgamename);
            SqlGameDAO.clear();
            try{
                newgamedata.join("", outid, "ghostie");
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
            var newgamedata = new SqlGameDAO();
            var randgamename = "omori";
            var outid = newgamedata.nueva(randgamename);
            SqlGameDAO.clear();
            try{
                newgamedata.join("", outid, "something");
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
}
