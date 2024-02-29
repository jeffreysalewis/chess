package service;

public class LogoutService {
    private String authtoken;

    LogoutService(String auth) {
        this.authtoken = auth;
    }
}
