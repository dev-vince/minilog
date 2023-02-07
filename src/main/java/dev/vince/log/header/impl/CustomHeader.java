package dev.vince.log.header.impl;

import dev.vince.log.header.AbstractHeader;
import dev.vince.log.util.parse.Parser;
import dev.vince.log.util.parse.ParsingBean;

public final class CustomHeader extends AbstractHeader {
    private String key;
    
    public CustomHeader(final String key) {
        super("");
        this.key = key;
    }

    @Override
    public String getHeader(final ParsingBean data) {
        data.setInput(key);
                
        return Parser.parse(data).getInput();
    }

    public final String getKey() {
        return key;
    }

    public final void setKey(final String key) {
        this.key = key;
    }
}
