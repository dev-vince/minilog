package dev.vince.log.logger.api;

import java.util.HashMap;
import java.util.Map;

import dev.vince.log.logger.LoggingLevelEnum;
import dev.vince.log.util.parse.Parser;
import dev.vince.log.util.parse.ParsingBean;

public final class LoggerFormat {
    private final Builder builder;

    public LoggerFormat(final Builder builder) {
        this.builder = builder;
    }

    public void setLogFormat(final String logFormat) {
        this.builder.logFormat = logFormat;
    }

    public void setLevelFormat(final LoggingLevelEnum level, final String format) {
        this.builder.levelFormats.replace(level, format);
    }

    public String getLogFormat() {
        return builder.logFormat;
    }

    public String getLevelFormat(final LoggingLevelEnum level) {
        return builder.levelFormats.get(level);
    }

    public String getFormat(final ParsingBean data) {
        return Parser.parse(data, builder.logFormat).getInput();
    }
    
    public LoggerFormat create() {
        return this;
    }

    public static final class Builder {
        private final Map<LoggingLevelEnum, String> levelFormats;
        private String logFormat;

        public Builder() {
            this.levelFormats = new HashMap<>();
            this.logFormat = "%L: %i";
            for (final LoggingLevelEnum level : LoggingLevelEnum.values()) {
                levelFormats.put(level, level.getDefaultFormat());
            }
        }

        public Builder withLogFormat(final String logFormat) {
            this.logFormat = logFormat;
            return this;
        }

        public Builder withLevelFormat(final LoggingLevelEnum level, final String format) {
            this.levelFormats.replace(level, format);
            return this;
        }

        public LoggerFormat build() {
            return new LoggerFormat(this);
        }
    }
}
