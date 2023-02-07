package dev.vince.log.text.impl.date;

import java.time.LocalDate;

import dev.vince.log.text.ParsingBean;
import dev.vince.log.text.api.AbstractTextFormat;

public final class MonthFormat extends AbstractTextFormat{
    public MonthFormat() {
        super("Month", "The month of the year", "%M");
    }

    @Override
    public String getText(final ParsingBean data) {
        return LocalDate.now().getMonthValue() + "";
    }
}
