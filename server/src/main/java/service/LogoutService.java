package service;
import dataAccess.*;
import exception.ResponseException;

public class LogoutService {

    public LogoutService() {
    }

    public void logout(String auth) throws ResponseException {
        try {
            if (SqlAuthDAO.authorize(auth)) {
                SqlAuthDAO.deleteAuth(auth);
            } else {
                throw new ResponseException(401, "Error: unauthorized");
            }
        } catch (ResponseException r) {
            throw new ResponseException(401, "Error: unauthorized");
        }
    }
}
