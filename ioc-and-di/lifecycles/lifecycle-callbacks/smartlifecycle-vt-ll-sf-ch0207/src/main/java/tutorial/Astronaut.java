package tutorial;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * A simple object with setup and tear down logic
 */
@Component
public class Astronaut implements SmartLifecycle {

    private Log log = LogFactory.getLog(getClass());

    private volatile boolean running = false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public void start() {
        try {
            this.running = true;
            for (int i = 5; i > 0; i--) {
                log.debug(i + "...");
                Thread.sleep(1000);
            }

            log.debug("we have liftoff!");
            System.out.println("we have liftoff!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        System.out.println("this is one small step for man... one giant leap for mankind");
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}


