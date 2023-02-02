package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.logger.Logger;

public final class NoneHeader extends AbstractHeader {
    public NoneHeader() {
        super("");
    }

    @Override
    public String getHeader(final Logger logger) {
        return "";
    }

}
