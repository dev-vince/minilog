package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class YearFormat extends AbstractTextFormat{
    public YearFormat() {
        super("Year", "The year", "%Y");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getYear() + "";
    }
}
