package dev.vince;

import dev.vince.log.header.LoggingHeaderEnum;
import dev.vince.log.logger.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.createLogger().withName("Logger Example").withHeader(new BracketedHeader()).build();

        logger.info("Hello world!");
    }
}