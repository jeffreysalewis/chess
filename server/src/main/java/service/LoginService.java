package service;

import dataAccess.*;
import exception.ResponseException;

public class LoginService {
    private String username;
    private String password;

    public LoginService(String usnm, String pwd) {
        this.username = usnm;
        this.password = pwd;
    }

    public String[] login () throws ResponseException {
        if(SqlUserDAO.userdata == null) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        if(SqlUserDAO.userdata.get(username) == null) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        if(!SqlUserDAO.userdata.get(username)[1].equals(this.password)) {
            throw new ResponseException(401, "Error: unauthorized");
        }
        var auth = new SqlAuthDAO();
        var a = auth.createAuth(username);
        var am = new String[]{username, a};
        //var am = auth.getUser(this.username);
        return am;
    }

    public String getUsername() {
        return this.username;
    }
}
