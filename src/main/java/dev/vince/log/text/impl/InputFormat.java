package dev.vince.log.text.impl;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class InputFormat extends AbstractTextFormat {
    public InputFormat() {
        super("Input", "The input of the message posted", "%i");
    }

    @Override
    public String getText(final ParsingBean data) {
        return data.getInput();
    }
}
