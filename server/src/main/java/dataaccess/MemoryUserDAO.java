package dataaccess;

public class MemoryUserDAO implements UserDAO{
    public MemoryUserDAO() {

    }

    @Override
    public String getUser() {
        return null;
    }

    public void createUser(String username, String password) {

    }

    public String createAuth(String username) {
        return "an authtoken";
    }
}
