package service;

import dataaccess.*;

public class Clear {
    public Clear() {

    }

    public void clear() {
        MemoryUserDAO.clear();
        MemoryAuthDAO.clear();
        MemoryGameDAO.clear();
    }
}
