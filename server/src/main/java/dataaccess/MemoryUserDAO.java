package dataaccess;

import java.util.*;

public class MemoryUserDAO implements UserDAO{
    private String username;
    private String authtoken;
    private HashMap<String, String[]> userdata;
    public MemoryUserDAO() {
        userdata = new HashMap<>();
    }

    @Override
    public String[] getUser(String username) {
        return null;
        //return userdata.get(username);
    }

    public void createUser(String username, String password, String email) {
        var ac = new String[]{username, password, email};
        userdata.put(username, ac);
    }
}
