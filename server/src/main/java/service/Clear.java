package service;

import dataAccess.*;
import exception.ResponseException;

public class Clear {
    public Clear() {

    }

    public int clear() throws ResponseException {
        SqlUserDAO.clear();
        SqlAuthDAO.clear();
        SqlGameDAO.clear();
        return 0;
    }
}
