package dataaccess;

import java.util.*;

public class MemoryUserDAO implements UserDAO{
    private String username;
    private String authtoken;
    private HashMap<String, String[]> userdata = new HashMap<>();
    public MemoryUserDAO() {
    }

    @Override
    public String[] getUser(String username) {
        if (userdata == null) {
            return null;
        }
        return userdata.get(username);
    }

    public void createUser(String username, String password, String email) {
        var ac = new String[]{username, password, email};
        userdata.put(username, ac);
    }
}
