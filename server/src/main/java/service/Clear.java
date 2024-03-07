package service;

import dataAccess.*;

public class Clear {
    public Clear() {

    }

    public int clear() {
        MemoryUserDAO.clear();
        MemoryAuthDAO.clear();
        MemoryGameDAO.clear();
        return 0;
    }
}
