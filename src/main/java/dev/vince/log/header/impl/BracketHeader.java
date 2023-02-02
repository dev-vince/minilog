package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.logger.Logger;

public final class BracketHeader extends AbstractHeader{
    public BracketHeader() {
        super("[%s]");
    }

    @Override
    public String getHeader(final Logger logger) {
        return String.format(this.getDefaultHeader(), logger.getName());
    }
    
}
