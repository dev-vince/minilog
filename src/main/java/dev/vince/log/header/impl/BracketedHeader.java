package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.util.parse.ParsingBean;

public final class BracketedHeader extends AbstractHeader{
    public BracketedHeader() {
        super("[%s] ");
    }

    @Override
    public String getHeader(final ParsingBean data) {
        return String.format(this.getDefaultHeader(), data.getLogger().getName());
    }
    
}
