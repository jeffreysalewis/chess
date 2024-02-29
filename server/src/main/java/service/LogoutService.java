package service;

public class LogoutService {
    private String authtoken;

    public LogoutService(String auth) {
        this.authtoken = auth;
    }

    public void logout() {

    }

    public String getauthtoken() {
        return this.authtoken;
    }
}
