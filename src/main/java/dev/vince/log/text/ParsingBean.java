package dev.vince.log.text;

import dev.vince.log.logger.Logger;
import dev.vince.log.logger.LoggingLevelEnum;
import lombok.Data;

@Data
public class ParsingBean {
    String input;
    Logger logger;
    LoggingLevelEnum level;
}
