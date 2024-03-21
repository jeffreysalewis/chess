package jsonObjClasses;

public class userAuth {
    private String username;
    private String authToken;

    public userAuth(String username, String authToken) {
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
