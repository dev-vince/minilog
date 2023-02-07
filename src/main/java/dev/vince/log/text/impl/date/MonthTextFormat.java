package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class MonthTextFormat extends AbstractTextFormat{
    public MonthTextFormat() {
        super("Month Text", "The name of the month of the year", "%MT");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getMonth().name();
    }
}
