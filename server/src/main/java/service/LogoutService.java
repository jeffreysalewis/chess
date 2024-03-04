package service;
import dataaccess.*;
import exception.ResponseException;

public class LogoutService {

    public LogoutService() {
    }

    public void logout(String auth) throws ResponseException {
        if(MemoryAuthDAO.authorize(auth)) {
            MemoryAuthDAO.deleteAuth(auth);
            return;
        } else {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
