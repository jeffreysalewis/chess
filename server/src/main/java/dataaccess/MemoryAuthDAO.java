package dataaccess;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    //private static int uuid = 0;
    private String authtoken;
    private static HashMap<String, String[]> authdata = new HashMap<>();
    public MemoryAuthDAO() {
        //MemoryAuthDAO.uuid += 1;
    }
    public String createAuth(String username) {
        //this.authtoken = Integer.toString(MemoryAuthDAO.uuid);
        //this.authtoken = Double.toString(Math.random());
        this.authtoken = UUID.randomUUID().toString();
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.put(username, ac);
        return this.authtoken;
    }

    public String[] getUser(String username) {
        //MemoryAuthDAO.uuid += 1;
        //if (MemoryAuthDAO.authdata == null) {
        //    return null;
        //}
        //this.authtoken = Integer.toString(MemoryAuthDAO.uuid);
        //this.authtoken = Double.toString(Math.random());
        this.authtoken = UUID.randomUUID().toString();
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.remove(username);
        MemoryAuthDAO.authdata.put(username, ac);
        var temp = MemoryAuthDAO.authdata.get(username);
        return temp;
    }

    public static boolean authorize(String inpauth) {
        for (var oneauthdata:MemoryAuthDAO.authdata.values()) {
            if(oneauthdata != null) {
                if(oneauthdata[1] != null) {
                    if (oneauthdata[1].equals(inpauth)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String getUserfromAuth(String auth) {
        for (var oneauthdata:MemoryAuthDAO.authdata.values()) {
            if(oneauthdata[1].equals(auth)) {
                return oneauthdata[0];
            }
        }
        return null;
    }

    public static void deleteAuth(String auth) {
        var userna = MemoryAuthDAO.getUserfromAuth(auth);
        String[] ac = {userna, null};
        MemoryAuthDAO.authdata.remove(userna);
        MemoryAuthDAO.authdata.put(userna, ac);
    }

    public static void clear() {
        //MemoryAuthDAO.uuid = 0;
        MemoryAuthDAO.authdata.clear();// = new HashMap<>();
    }
}
