package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.ParsingBean;
import dev.vince.log.text.api.AbstractTextFormat;

public final class DayFormat extends AbstractTextFormat{
    public DayFormat() {
        super("Day", "The day of the month", "%D");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getDayOfMonth() + "";
    }
}
