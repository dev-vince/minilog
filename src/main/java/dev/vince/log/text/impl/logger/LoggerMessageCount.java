package dev.vince.log.text.impl.logger;

import dev.vince.log.text.AbstractTextFormat;
import dev.vince.log.text.ParsingBean;

public final class LoggerMessageCount extends AbstractTextFormat{
    public LoggerMessageCount() {
        super("Logger Message Count", "The number of messages sent by the logger", "%C");
    }

    @Override
    public String getText(final ParsingBean data) {
        return data.getLogger().getMessageCount() + "";
    }
}
