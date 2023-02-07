package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class DayTextFormat extends AbstractTextFormat{
    public DayTextFormat() {
        super("Day", "The name of the day of the month", "%DT");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getDayOfWeek().name();
    }
}
