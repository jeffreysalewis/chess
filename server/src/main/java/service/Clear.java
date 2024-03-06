package service;

import dataaccess.*;

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
