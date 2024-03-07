package dataAccess;

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

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `authtoken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              PRIMARY KEY (`username`),
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };
}
