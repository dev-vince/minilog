import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.hook.Hook;
import dev.vince.log.hook.api.AbstractLoggerHook;
import dev.vince.log.logger.api.Logger;
import dev.vince.log.logger.api.LoggerManager;

@Hook
public class LoggerHookExample extends AbstractLoggerHook {
    public LoggerHookExample() {
        super("Example hook for a specific logger");
    }

    @Override
    public void hook(final Logger logger, final LoggerEventEnum type) {
        super.setLogger(LoggerManager.getLogger("Logger Example")); //This is the logger that will be hooked

        if(logger.equals(super.getLogger())){
            if(type == LoggerEventEnum.PRE){
                System.out.println("This is a pre hook, it will be executed before the logger logs the message");
            }

            if(type == LoggerEventEnum.POST){
                System.out.println("This is a post hook, it will be executed after the logger logs the message");
            }
        }
    }
}
