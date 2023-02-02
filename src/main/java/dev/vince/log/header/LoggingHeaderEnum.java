package dev.vince.log.header;

import dev.vince.log.header.impl.BracketedHeader;
import dev.vince.log.header.impl.NoneHeader;

public enum LoggingHeaderEnum {
    NONE(NoneHeader.class),
    BRACKETED(BracketedHeader.class),
    SIMPLE_NAME(BracketedHeader.class);

    private final Class<?> defaultHeader;

    LoggingHeaderEnum(final Class<?> defaultHeader) {
        this.defaultHeader = defaultHeader;
    }

    public final AbstractHeader getDefaultHeader() {
        try {
            return (AbstractHeader) defaultHeader.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

}