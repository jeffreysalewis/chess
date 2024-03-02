package dataaccess;

public class MemoryAuthDAO implements AuthDAO {
    private static int uuid = 0;
    private String authtoken;
    public MemoryAuthDAO() {
        MemoryAuthDAO.uuid += 1;
    }
    public String createAuth(String username) {
        this.authtoken = Integer.toString(MemoryAuthDAO.uuid);
        return this.authtoken;
    }
}
