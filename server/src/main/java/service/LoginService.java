package service;

import dataaccess.*;
import exception.ResponseException;

public class LoginService {
    private String username;
    private String password;

    public LoginService(String usnm, String pwd) {
        this.username = usnm;
        this.password = pwd;
    }

    public String[] login () throws ResponseException {
        if(MemoryUserDAO.userdata == null) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        if(MemoryUserDAO.userdata.get(username) == null) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        if(!MemoryUserDAO.userdata.get(username)[1].equals(this.password)) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        var auth = new MemoryAuthDAO();
        var am = auth.getUser(this.username);
        return am;
    }

    public String getUsername() {
        return this.username;
    }
}
