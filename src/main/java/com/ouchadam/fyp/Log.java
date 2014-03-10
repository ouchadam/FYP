package com.ouchadam.fyp;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private static final Logger LOGGER = Logger.getGlobal();

    public static void e(String message, Exception e) {
        LOGGER.log(Level.SEVERE, message, e);
    }

    public static void e(String message) {
        LOGGER.log(Level.SEVERE, message);
    }

    public static void w(String message, Exception e) {
        LOGGER.log(Level.WARNING, message, e);
    }

    public static void i(String message) {
        LOGGER.log(Level.INFO, message);
    }
}
