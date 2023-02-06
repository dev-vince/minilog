package dev.vince.log.text.impl.logger;

import dev.vince.log.text.AbstractTextFormat;
import dev.vince.log.text.ParsingBean;

public final class LoggerNameFormat extends AbstractTextFormat {
    public LoggerNameFormat() {
        super("Logger Name", "The name of the logger", "%N");
    }
    
    
    @Override
    public String getText(final ParsingBean data) {
        return data.getLogger().getName();
    }
}
