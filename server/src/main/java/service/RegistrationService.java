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

    public String registerUser() throws Exception {
        if(this.username == null || this.password == null || this.email == null) {
            throw new Exception("400 Error: bad request");
        }
        var user = new MemoryUserDAO();
        if(user.getUser(this.username) == null) {
            user.createUser(this.username, this.password, this.email);
            var auth = new MemoryAuthDAO();
            var temp = auth.createAuth(this.username);
            return temp;
        } else {
            throw new ResponseException(403, "Error: already taken");
        }
    }

    public void createUser() {

    }

    public void createAuth() {

    }

    public String getauthtoken() throws ResponseException {
        return null; //this.registerUser();
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
