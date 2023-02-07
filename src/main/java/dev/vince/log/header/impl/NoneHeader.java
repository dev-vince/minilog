package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.util.parse.ParsingBean;

public final class NoneHeader extends AbstractHeader {
    public NoneHeader() {
        super("");
    }

    @Override
    public String getHeader(final ParsingBean data) {
        return "";
    }

}
