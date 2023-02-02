package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.logger.Logger;

public final class BracketedHeader extends AbstractHeader{
    public BracketedHeader() {
        super("[%s] ");
    }

    @Override
    public String getHeader(final Logger logger) {
        return String.format(this.getDefaultHeader(), logger.getName());
    }
    
}
