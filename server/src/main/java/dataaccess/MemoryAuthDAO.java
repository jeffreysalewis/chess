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
        if (MemoryAuthDAO.authdata == null) {
            return null;
        }
        var temp = MemoryAuthDAO.authdata.get(username);
        return temp;
    }
}
