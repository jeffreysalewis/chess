package dataaccess;

public interface UserDAO {
    public String getUser();
    public void createUser(String username, String password);
    public String createAuth(String username);
}
