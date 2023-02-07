package dev.vince.log.text.impl.logger;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class LoggingLevelFormat extends AbstractTextFormat{
    public LoggingLevelFormat() {
        super("Logging Level", "The logging level of a posted log", "%L");
    }

    @Override
    public String getText(final ParsingBean data) {
        return data.getLevel().toString();
    }
}
