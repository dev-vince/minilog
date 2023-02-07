package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.ParsingBean;
import dev.vince.log.text.api.AbstractTextFormat;

public final class DayTextFormat extends AbstractTextFormat{
    public DayTextFormat() {
        super("Day", "The name of the day of the month", "%DT");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getDayOfWeek().name();
    }
}
