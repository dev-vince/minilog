package dev.vince.log;

import dev.vince.log.logger.Logger;

import java.util.ArrayList;

public final class LoggerManager {
    private static final ArrayList<Logger> loggers = new ArrayList<>();

    public static ArrayList<Logger> getLoggers() {
        return loggers;
    }

    public static void addLogger(final Logger logger) {
        loggers.add(logger);
    }

    public static void removeLogger(final Logger logger) {
        loggers.remove(logger);
    }

    public static ArrayList<Logger> getLoggersByName(final String name) {
        return LoggerManager.loggers.stream().filter(logger -> logger.getName().equals(name)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public static Logger getLogger(final String name) {
        return loggers.stream().filter(logger -> logger.getName().equals(name)).findFirst().orElse(null);
    }
}
