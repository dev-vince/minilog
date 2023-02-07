package dev.vince.log.text.impl.logger;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class LoggerMessageCount extends AbstractTextFormat{
    public LoggerMessageCount() {
        super("Logger Message Count", "The number of messages sent by the logger", "%C");
    }

    @Override
    public String getText(final ParsingBean data) {
        return data.getLogger().getMessageCount() + "";
    }
}
