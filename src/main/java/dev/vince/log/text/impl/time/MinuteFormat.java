package dev.vince.log.text.impl.time;

import java.time.LocalDateTime;

import dev.vince.log.text.AbstractTextFormat;
import dev.vince.log.text.ParsingBean;

public final class MinuteFormat extends AbstractTextFormat{
    public MinuteFormat() {
        super("Minute", "The minute of the hour", "%m");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDateTime.now().getMinute() + "";
    }
}
