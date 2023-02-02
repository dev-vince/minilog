import java.io.FileNotFoundException;
import java.io.PrintStream;

import dev.vince.log.logger.Logger;
import dev.vince.log.logger.LoggerFormat;
import dev.vince.log.util.LoggingLevelEnum;

public class LoggerTester {
    public static void main(String[] args) throws FileNotFoundException {
        final Logger newLogger = Logger.createLogger()
                .withName("New Logger")
                .build();

        final Logger loggerWIthLogLevel = Logger.createLogger()
                .withName("Logger with log level")
                .withLogLevel(LoggingLevelEnum.WARN)
                .build();
        
        final Logger loggerWithFormatExample = Logger.createLogger()
                .withName("Logger with custom format")
                .withLogLevel(LoggingLevelEnum.WARN)
                .withFormat(new LoggerFormat.Builder()
                    .withLogFormat("(%s): %s")// The first %s is the log level and the second is the message, The default format is "%s: %s"
                    .withLevelFormat(LoggingLevelEnum.WARN, "WARNING") //Changes the format of the log level to WARNING (by default WARN)
                    .build()) 
                .build();
        
        final Logger logger = Logger.createLogger()
                .withName("Logger Example")
                .withLogLevel(LoggingLevelEnum.WARN)
                .withFormat(new LoggerFormat.Builder()
                    .withLogFormat("(%s): %s")// The first %s is the log level and the second is the message, The default format is "%s: %s"
                    .withLevelFormat(LoggingLevelEnum.WARN, "WARNING") //Changes the format of the log level to WARNING (by default WARN)
                    .build())
                .withOutput(new PrintStream("output.txt")) //Will now add all logs to output.txt
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