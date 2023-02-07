package dev.vince.log.text.impl.time;

import java.time.LocalDateTime;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class SecondFormat extends AbstractTextFormat {
    public SecondFormat() {
        super("Second", "The second of the minute", "%s");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDateTime.now().getSecond() + "";
    }
}
