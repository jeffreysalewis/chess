package jsonObjClasses;

public class UserAuth {
    private String username;
    private String authToken;

    public UserAuth(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAuthToken() {
        return this.authToken;
    }
}
