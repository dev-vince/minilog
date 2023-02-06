package dev.vince.log.text;

import java.util.ArrayList;
import java.util.List;

import dev.vince.log.text.impl.InputFormat;
import dev.vince.log.text.impl.date.DayFormat;
import dev.vince.log.text.impl.date.DayTextFormat;
import dev.vince.log.text.impl.date.MonthFormat;
import dev.vince.log.text.impl.date.MonthTextFormat;
import dev.vince.log.text.impl.date.YearFormat;
import dev.vince.log.text.impl.logger.LoggerMessageCount;
import dev.vince.log.text.impl.logger.LoggerNameFormat;
import dev.vince.log.text.impl.logger.LoggingLevelFormat;
import dev.vince.log.text.impl.time.HourFormat;
import dev.vince.log.text.impl.time.MinuteFormat;
import dev.vince.log.text.impl.time.SecondFormat;

public enum TextFormattingEnum {
    NAME(LoggerNameFormat.class),
    MESSAGE_COUNT(LoggerMessageCount.class),
    LOGGING_LEVEL(LoggingLevelFormat.class),
    DAY(DayFormat.class),
    MONTH(MonthFormat.class),
    YEAR(YearFormat.class),
    DAY_TEXT(DayTextFormat.class),
    MONTH_TEXT(MonthTextFormat.class),
    HOUR(HourFormat.class),
    MINUTE(MinuteFormat.class),
    SECOND(SecondFormat.class),
    INPUT(InputFormat.class);
    
    private final Class<? extends AbstractTextFormat> clazz;
    private final AbstractTextFormat instance;

    TextFormattingEnum(final Class<? extends AbstractTextFormat> clazz) {
        this.clazz = clazz;
        try {
            this.instance = clazz.newInstance();
            FormattingCacheManager.registerFormat(instance);
        } catch (final InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error while creating a new instance of " + clazz.getName() + "!");
        }

        System.out.println();
        for(AbstractTextFormat format : FormattingCacheManager.getFormats()) {
            System.out.println(format.getKey() + " - " + format.getDescription());
        }
    }

    public final Class<? extends AbstractTextFormat> getClazz() {
        return clazz;
    }

    public final AbstractTextFormat getFormat() {
        return instance;
    }

    @Override
    public final String toString() {
        return instance.getKey();
    }
    
    public class FormattingCacheManager {
        private static final ArrayList<AbstractTextFormat> formatsCache = new ArrayList<>();
        
        private FormattingCacheManager() {}

        public static void registerFormat(final AbstractTextFormat format) {
            formatsCache.add(format);
        }
        
        public static List<AbstractTextFormat> getFormats() {
            return formatsCache;
        }

        public static void clearFormats() {
            formatsCache.clear();
        }
    }
}


