package service;

public class RegistrationService {
    private String username;
    private String password;
    private String email;
    private String authtoken;
    public RegistrationService(String usnm, String pwd, String cor) {
        this.username = usnm;
        this.password = pwd;
        this.email = cor;
        this.authtoken = this.email + " correo";
    }

    public String getauthtoken() {
        return this.authtoken;
    }
}
