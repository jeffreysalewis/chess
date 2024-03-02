package dataaccess;

import java.util.*;

public class MemoryUserDAO implements UserDAO{
    private String username;
    private String authtoken;
    private static HashMap<String, String[]> userdata = new HashMap<>();
    public MemoryUserDAO() {
    }

    @Override
    public String[] getUser(String username) {
        if (MemoryUserDAO.userdata == null) {
            return null;
        }
        var temp = MemoryUserDAO.userdata.get(username);
        return temp;
    }

    public void createUser(String username, String password, String email) {
        var ac = new String[]{username, password, email};
        MemoryUserDAO.userdata.put(username, ac);
    }
}
