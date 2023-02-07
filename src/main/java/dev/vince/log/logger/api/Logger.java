package dev.vince.log.logger.api;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.vince.log.MiniLog;
import dev.vince.log.event.LoggerEvent;
import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.header.AbstractHeader;
import dev.vince.log.header.impl.NoneHeader;
import dev.vince.log.logger.LoggingLevelEnum;
import dev.vince.log.util.parse.ParsingBean;

public class Logger {
    private final Builder builder;
    
    private int messageCount;

    /**
     * Creates a new Logger instance with the given builder.
     * @param builder The builder of the logger.
     * (Precondition: builder != null)
     * (Post condition: LoggingManager contains the new Logger instance.)
     */
    public Logger(final Builder builder) {
        this.builder = builder;
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level TRACE.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void trace(final String... messages) {
        postLog(LoggingLevelEnum.TRACE, messages);
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level DEBUG.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void debug(final String... messages) {
        postLog(LoggingLevelEnum.DEBUG, messages);
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level INFO.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void info(final String... messages) {
        postLog(LoggingLevelEnum.INFO, messages);
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level WARN.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void warn(final String... messages) {
        postLog(LoggingLevelEnum.WARN, messages);
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level ERROR.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void error(final String... messages) {
        postLog(LoggingLevelEnum.ERROR, messages);
    }

    /**
     * Sends a message to the logger's output streams
     * The log be sent with the logging level FATAL.
     * @param messages the messages to send to the output streams (can be multiple)
     */
    public void fatal(final String... messages) {
        postLog(LoggingLevelEnum.FATAL, messages);
    }

    public void postLog(final LoggingLevelEnum level, final String... messages) {
        for (final String message : messages) {
            postLog(level, message);
        }
    }

    private void postLog(final LoggingLevelEnum level, final String message) {
        if (level.getLevel() >= builder.logLevel) {
            for (final PrintStream output : builder.outputs) {
                final LoggerEvent event = new LoggerEvent(LoggerEventEnum.PRE, this);
                final ParsingBean bean = new ParsingBean();
                

                bean.setInput(message);
                bean.setLogger(this);
                bean.setLevel(level);

                messageCount++;

                MiniLog.getInstance().getEventBus().call(event);
                output.println(builder.format.getFormat(bean));
                event.setType(LoggerEventEnum.POST);
                MiniLog.getInstance().getEventBus().call(event);
            }
        }
    }

    public List<PrintStream> getOutputs() {
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

    public int getMessageCount() {
        return messageCount;
    }

    @Deprecated        
    public AbstractHeader getHeader() {
        return builder.header;
    }

    public void setLogLevel(final int logLevel) {
        builder.logLevel = logLevel;
    }

    public void setFormat(final LoggerFormat format) {
        builder.format = format;
    }

    public void setName(final String name) {
        builder.name = name;
    }

    @Deprecated        
    public void setHeader(final AbstractHeader header) {
        builder.header = header;
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

        private AbstractHeader header;
        private LoggerFormat format;
        private int logLevel;

        public Builder() {
            this.outputs = new ArrayList<>();
            this.name = "Logger";
            this.logLevel = 0;
            this.format = new LoggerFormat.Builder().build();
            this.header = new NoneHeader();

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

        public Builder withLogLevel(final LoggingLevelEnum logLevel) {
            this.logLevel = logLevel.getLevel();
            return this;
        }

        public Builder withOutput(final PrintStream output) {
            this.outputs.add(output);
            return this;
        }

        public Builder withoutOutput(final PrintStream output) {
            this.outputs.remove(output);
            return this;
        }

        public Builder withOutputs(final PrintStream... outputs) {
            this.outputs.addAll(Arrays.asList(outputs));
            return this;
        }

        public Builder withoutOutputs(final PrintStream... outputs) {
            this.outputs.removeAll(Arrays.asList(outputs));
            return this;
        }

        public Builder clearOutputs() {
            this.outputs.clear();
            return this;
        }

        @Deprecated
        public Builder withHeader(final AbstractHeader header) {
            this.header = header;
            return this;
        }

        public Logger build() {
            final Logger logger = new Logger(this);

            MiniLog.getInstance().getCacheManager().addLogger(logger);

            if(MiniLog.getInstance().getInternalLogger() != null) {
                MiniLog.getInstance().getHookManager().loadHooks();
                MiniLog.getInstance().getInternalLogger().info("Logger created with the name " + logger.builder.name);
            }

            return logger;
        }
    }

    public static Builder createLogger() {
        return new Builder();
    }

    public static Logger getLogger(final String name) {
        return MiniLog.getInstance().getCacheManager().hasLogger(name) ?
                MiniLog.getInstance().getCacheManager().getLogger(name) :
                Logger.createLogger().withName(name).build();
    }

    public static Logger getLoggerWithoutCreate(final String name) {
        return MiniLog.getInstance().getCacheManager().getLogger(name);
    }

    public static class LoggerCacheManager {
        private final ArrayList<Logger> loggerCache = new ArrayList<>();
        
        public LoggerCacheManager() {}
        
        public void addLogger(final Logger logger) {
            loggerCache.add(logger);
        }

        public boolean hasLogger(final String name){
            return loggerCache.stream().anyMatch(logger -> logger.getName().equals(name));
        }

        public Logger getLogger(final String name) {
            return loggerCache.stream().filter(logger -> logger.getName().equals(name)).findFirst().orElse(null);
        }
    }
}
