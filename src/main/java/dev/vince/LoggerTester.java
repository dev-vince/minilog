package dev.vince;

import dev.vince.log.LoggerManager;
import dev.vince.log.logger.Logger;
import dev.vince.log.logger.LoggerFormat;
import dev.vince.log.util.LoggingLevelEnum;

public class LoggerTester {
    public static void main(String[] args) {
        final Logger logger = Logger.createLogger().build();
        logger.info("Hello, world!");
    }
}