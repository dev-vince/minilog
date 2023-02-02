import dev.vince.log.logger.Logger;
import dev.vince.log.logger.LoggerFormat;
import dev.vince.log.util.LoggingLevelEnum;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class LoggerTester {
    public static void main(String[] args) throws FileNotFoundException {
        final Logger logger = Logger.createLogger()
                                    .withName("Logging Example")
                                    .withFormat(new LoggerFormat.Builder()
                                            //.withLogFormat("(%s): %s")
                                            //.withLevelFormat(LoggingLevelEnum.WARN, "WARNING")
                                            .build())
                                    .withOutput(new PrintStream("output.txt"))
                                    .build();

        logger.trace("This is a trace message");
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");

        logger.trace("This is a trace message", "This is another trace message");
        logger.debug("This is a debug message", "This is another debug message");
        logger.info("This is an info message", "This is another info message");
        logger.warn("This is a warning message", "This is another warning message");
        logger.error("This is an error message", "This is another error message");
        logger.fatal("This is a fatal message", "This is another fatal message");

        logger.info("Line 1","Line 2","Line 3");
    }
}