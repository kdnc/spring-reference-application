package hello;

import org.joda.time.LocalTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting() {
        LocalTime currentTime = new LocalTime();
        Greeter greeter = new Greeter();
        return currentTime + " " + greeter.sayHello();
    }

}
