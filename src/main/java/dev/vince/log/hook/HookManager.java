package dev.vince.log.hook;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import dev.vince.log.util.ClassUtil;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.log.event.LoggerEvent;
import dev.vince.log.hook.api.AbstractHook;

public final class HookManager {
    private static final EventBus eventBus = new EventBus();
    private static final HookManager instance = new HookManager();

    private final Map<String, AbstractHook> hooks = new HashMap<>();

    private HookManager() {
        eventBus.register(this);
    }

    public void loadHooks() {
        this.hooks.clear();
        for (Class<?> clazz : ClassUtil.getClassesRunning()) {
            if (clazz.isAnnotationPresent(Hook.class)) {
                try {
                    addHook((AbstractHook) clazz.newInstance());
                } catch (final InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addHook(final AbstractHook hook) {
        hooks.put(hook.getName(), hook);
    }

    public AbstractHook getHook(final String name) {
        return hooks.get(name);
    }

    public void removeHook(final String name) {
        hooks.remove(name);
    }

    public void removeHook(final AbstractHook hook) {
        hooks.remove(hook.getName());
    }

    public void clearHooks() {
        hooks.clear();
    }

    public void callEvent(final LoggerEvent event) {
        eventBus.call(event);
    }

    @EventHandler()
    private final Listener<LoggerEvent> loggerEventListener = e -> {
        for(final AbstractHook hook : hooks.values()) {
            hook.hook(e.getLogger(), e.getType());
        }
    };

    public static HookManager getInstance() {
        return instance;
    }
}
