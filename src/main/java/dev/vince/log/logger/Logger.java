package dev.vince.log.logger;

import dev.vince.log.LoggerManager;
import dev.vince.log.util.LoggingLevelEnum;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private final Builder builder;

    public Logger(final Builder builder) {
        this.builder = builder;
        LoggerManager.addLogger(this);
    }

    public void trace(final String... messages) {
        postLog(LoggingLevelEnum.TRACE, messages);
    }

    public void debug(final String... messages) {
        postLog(LoggingLevelEnum.DEBUG, messages);
    }

    public void info(final String... messages) {
        postLog(LoggingLevelEnum.INFO, messages);
    }

    public void warn(final String... messages) {
        postLog(LoggingLevelEnum.WARN, messages);
    }

    public void error(final String... messages) {
        postLog(LoggingLevelEnum.ERROR, messages);
    }

    public void fatal(final String... messages) {
        postLog(LoggingLevelEnum.FATAL, messages);
    }

    public void postLog(final LoggingLevelEnum level, final String... messages) {
        for (final String message : messages) {
            postLog(level, message);
        }
    }

    public void postLog(final LoggingLevelEnum level, final String message) {
        if (level.getLevel() >= builder.logLevel) {
            for (final PrintStream output : builder.outputs) {
                output.println(String.format("[%s] " + builder.format.getLogFormat(), builder.name, builder.format.getLevelFormat(level), message));
            }
        }
    }

    public ArrayList<PrintStream> getOutputs() {
        return builder.outputs;
    }

    public int getLogLevel() {
        return builder.logLevel;
    }

    public LoggerFormat getFormat() {
        return builder.format;
    }

    public String getName() {
        return builder.name;
    }

    public void setLogLevel(final int logLevel) {
        builder.logLevel = logLevel;
    }

    public void setFormat(final LoggerFormat format) {
        builder.format = format;
    }

    public void addOutput(final PrintStream output) {
        builder.outputs.add(output);
    }

    public void removeOutput(final PrintStream output) {
        builder.outputs.remove(output);
    }

    public void clearOutputs() {
        builder.outputs.clear();
    }

    public static final class Builder {
        private final ArrayList<PrintStream> outputs;
        private String name;

        private LoggerFormat format;
        private int logLevel;

        public Builder() {
            this.outputs = new ArrayList<>();
            this.name = ManagementFactory.getRuntimeMXBean().getName();
            this.logLevel = 0;
            this.format = new LoggerFormat.Builder().build();

            outputs.add(System.out);
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withFormat(final LoggerFormat format) {
            this.format = format;
            return this;
        }

        public Builder withLogLevel(final int logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder withOutput(final PrintStream output) {
            this.outputs.add(output);
            return this;
        }

        public Logger build() {
            return new Logger(this);
        }
    }

    public static Builder createLogger() {
        return new Builder();
    }

    public static Logger getLogger(final String name) {
        return  LoggerManager.getLoggersByName(name).get(0);
    }
}
