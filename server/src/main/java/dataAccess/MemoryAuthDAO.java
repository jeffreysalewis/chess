package dataAccess;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    //private static int uuid = 0;
    private String authtoken;
    private static HashMap<String, String[]> authdata = new HashMap<>();
    public MemoryAuthDAO() {
    }
    public String createAuth(String username) {
        this.authtoken = UUID.randomUUID().toString();
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.put(this.authtoken, ac);
        return this.authtoken;
    }

    public String[] getUser(String username) {
        this.authtoken = UUID.randomUUID().toString();
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.put(this.authtoken, ac);
        var temp = MemoryAuthDAO.authdata.get(this.authtoken);
        return temp;
    }

    public static boolean authorize(String inpauth) {
        if(MemoryAuthDAO.authdata.get(inpauth) != null) {
            return true;
        }
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
        return MemoryAuthDAO.authdata.get(auth)[0];
    }

    public static void deleteAuth(String auth) {
        MemoryAuthDAO.authdata.remove(auth);
    }

    public static void clear() {
        MemoryAuthDAO.authdata.clear();// = new HashMap<>();
    }
}
