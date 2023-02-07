package dev.vince.log.header;

import dev.vince.log.util.parse.ParsingBean;

@Deprecated
public abstract class AbstractHeader {
    private final String defaultHeader;
    private String currentHeader;

    protected AbstractHeader(final String defaultHeader) {
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

    public abstract String getHeader(final ParsingBean data);
}
