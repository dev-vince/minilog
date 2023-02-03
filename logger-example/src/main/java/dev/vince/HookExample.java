package dev.vince;


import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.hook.Hook;
import dev.vince.log.hook.api.AbstractHook;
import dev.vince.log.logger.Logger;

@Hook
public class HookExample extends AbstractHook {
    public HookExample() {
        super("Example hook");
    }

    @Override
    public void hook(final Logger logger, final LoggerEventEnum type) {
        if (type == LoggerEventEnum.PRE) {
            System.out.println("This is a pre hook, it will be executed before the logger logs the message");
        }

        if (type == LoggerEventEnum.POST) {
            System.out.println("This is a post hook, it will be executed after the logger logs the message");
        }
    }
}
