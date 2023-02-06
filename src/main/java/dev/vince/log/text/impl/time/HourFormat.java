package dev.vince.log.text.impl.time;

import java.time.LocalDateTime;

import dev.vince.log.text.AbstractTextFormat;
import dev.vince.log.text.ParsingBean;

public final class HourFormat extends AbstractTextFormat {
    public HourFormat() {
        super("Hour", "The hour of the day", "%H");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDateTime.now().getHour() + "";
    }
}
