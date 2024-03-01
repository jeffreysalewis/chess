package service;
import dataaccess.*;
import exception.ResponseException;

public class RegistrationService {
    private String username;
    private String password;
    private String email;
    //private String authtoken;
    public RegistrationService(String usnm, String pwd, String cor) {
        this.username = usnm;
        this.password = pwd;
        this.email = cor;
        //this.authtoken = this.email + " correo";
    }

    public String registerUser() throws ResponseException {
        var user = new MemoryUserDAO();
        if(user.getUser() == null) {
            user.createUser(this.username, this.password);
            return user.createAuth(this.username);
        } else {
            throw new ResponseException(403, "Error: already taken");
        }
    }

    public void createUser() {

    }

    public void createAuth() {

    }

    public String getauthtoken() {
        return "jeffy";
    }

    public String getusername() {
        return this.username;
    }

    public String getpassword() {
        return this.password;
    }

    public String getemail() {
        return this.email;
    }
}
