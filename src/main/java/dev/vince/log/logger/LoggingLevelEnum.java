package dev.vince.log.logger;

public enum LoggingLevelEnum {
    TRACE(0, "LOG"),
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR"),
    FATAL(5, "FATAL");

    private final int level;
    private final String defaultFormat;

    LoggingLevelEnum(final int level, final String defaultFormat) {
        this.level = level;
        this.defaultFormat = defaultFormat;
    }

    public final int getLevel() {
        return level;
    }

    public final String getDefaultFormat() {
        return defaultFormat;
    }
}
