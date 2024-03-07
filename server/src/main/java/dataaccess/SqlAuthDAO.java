package dataaccess;

public class SqlAuthDAO implements AuthDAO{
    public SqlAuthDAO() {

    }

    @Override
    public String createAuth(String username) {
        return null;
    }

    @Override
    public String[] getUser(String username) {
        return new String[0];
    }
}
