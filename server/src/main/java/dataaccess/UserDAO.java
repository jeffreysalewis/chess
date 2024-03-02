package dataaccess;

public interface UserDAO {
    public Object getUser(String username);
    public void createUser(String username, String password, String email);

}
