package dataaccess;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {
    private static int uuid = 0;
    private String authtoken;
    private static HashMap<String, String[]> authdata = new HashMap<>();
    public MemoryAuthDAO() {
        MemoryAuthDAO.uuid += 1;
    }
    public String createAuth(String username) {
        this.authtoken = Integer.toString(MemoryAuthDAO.uuid);
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.put(username, ac);
        return this.authtoken;
    }

    public String[] getUser(String username) {
        //MemoryAuthDAO.uuid += 1;
        if (MemoryAuthDAO.authdata == null) {
            return null;
        }
        this.authtoken = Integer.toString(MemoryAuthDAO.uuid);
        String[] ac = {username, this.authtoken};
        MemoryAuthDAO.authdata.remove(username);
        MemoryAuthDAO.authdata.put(username, ac);
        var temp = MemoryAuthDAO.authdata.get(username);
        return temp;
    }

    public static boolean authorize(String inpauth) {
        for (var userkey:MemoryAuthDAO.authdata.keySet()) {
            if(MemoryAuthDAO.authdata.get(userkey)[1].equals(inpauth)) {
                return true;
            }
        }
        return false;
    }
}
