package dev.vince.log.header;

import dev.vince.log.logger.Logger;

public abstract class AbstractHeader {
    private final String defaultHeader;
    private String currentHeader;

    public AbstractHeader(final String defaultHeader) {
        this.defaultHeader = defaultHeader;
        this.currentHeader = defaultHeader;
    }

    public final String getDefaultHeader() {
        return defaultHeader;
    }

    public final String getCurrentHeader() {
        return currentHeader;
    }

    public final void getCurrentHeader(final String header) {
        this.currentHeader = header;
    }

    public abstract String getHeader(final Logger logger);
}
