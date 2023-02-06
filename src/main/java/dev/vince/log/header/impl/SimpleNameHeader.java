package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.text.ParsingBean;

public final class SimpleNameHeader extends AbstractHeader{
    public SimpleNameHeader() {
        super("%s ");
    }

    @Override
    public String getHeader(final ParsingBean data) {
        return String.format(this.getDefaultHeader(), data.getLogger().getName());
    }
}
