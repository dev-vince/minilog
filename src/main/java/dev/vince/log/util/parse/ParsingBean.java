package dev.vince.log.util.parse;

import dev.vince.log.logger.LoggingLevelEnum;
import dev.vince.log.logger.api.Logger;
import lombok.Data;

@Data
public class ParsingBean {
    String input;
    Logger logger;
    LoggingLevelEnum level;
}
