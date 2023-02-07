package dev.vince.log.text.impl.logger;

import dev.vince.log.text.api.AbstractTextFormat;
import dev.vince.log.util.parse.ParsingBean;

public final class LoggerNameFormat extends AbstractTextFormat {
    public LoggerNameFormat() {
        super("Logger Name", "The name of the logger", "%N");
    }
    
    
    @Override
    public String getText(final ParsingBean data) {
        return data.getLogger().getName();
    }
}
