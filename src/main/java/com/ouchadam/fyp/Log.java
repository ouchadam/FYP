package com.ouchadam.fyp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static final Logger LOGGER = create();

    private static Logger create() {
        Logger logger = Logger.getLogger("FYP");
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
        return logger;
    }

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

    public static void d(String message) {
        System.out.println(message);
//        LOGGER.log(Level.FINE, message);
    }
}
