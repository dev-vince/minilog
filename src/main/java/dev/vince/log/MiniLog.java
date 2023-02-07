package dev.vince.log;

import best.azura.eventbus.core.EventBus;
import dev.vince.log.hook.HookManager;
import dev.vince.log.logger.api.Logger;
import dev.vince.log.logger.api.Logger.LoggerCacheManager;
import dev.vince.log.logger.api.LoggerFormat;
import dev.vince.log.text.TextFormattingEnum.FormattingCacheManager;
public final class MiniLog {
    public static final String NAME = "MiniLog", VERSION = "1.1.0";

    private static final MiniLog instance = new MiniLog();
    
    private final FormattingCacheManager formattingCacheManager;
    private final LoggerCacheManager cacheManager;
    private final HookManager hookManager;

    private final EventBus eventBus;

    private Logger internalLogger;

    protected MiniLog() {
        this.eventBus = new EventBus();
        this.cacheManager = new LoggerCacheManager();
        this.formattingCacheManager = new FormattingCacheManager();
        this.hookManager = new HookManager(eventBus);
    }
    
    public LoggerCacheManager getCacheManager() {
        return cacheManager;
    }

    public HookManager getHookManager() {
        return hookManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Logger getInternalLogger() {
        return internalLogger;
    }

    public FormattingCacheManager getFormattingCacheManager() {
        return formattingCacheManager;
    }

    public void enableInternalLogger() {
        if(internalLogger == null)
            this.internalLogger = Logger.createLogger()
            .clearOutputs()
            .withName(NAME)
            .withFormat(new LoggerFormat.Builder()
                .withLogFormat("[%N] %H:%m:%s %Y-%M-%D         %L  :      %i")
                .build())
            .build();
        
        if(!internalLogger.getOutputs().contains(System.out))
            internalLogger.addOutput(System.out);

        internalLogger.info("Internal logger enabled!");
    }

    public void disableInternalLogger() {
        if(internalLogger.getOutputs().contains(System.out))
            internalLogger.removeOutput(System.out);

        internalLogger.info("Internal logger disabled!");
    }

    
    public static MiniLog getInstance() {
        return instance;
    }
}
