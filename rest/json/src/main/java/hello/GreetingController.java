package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//This code uses Spring 4’s new @RestController annotation. It’s shorthand for @Controller and @ResponseBody
//rolled together.

//In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller.
//These components are easily identified by the @RestController annotation
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    Spring uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON.

//    The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the
//    greeting() method.

//    @RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) to narrow this mapping.

//    @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        This RESTful web service controller simply populates and returns a Greeting object.
//        The object data will be written directly to the HTTP response as JSON.

//        Thanks to Spring’s HTTP message converter support, you don’t need to do this conversion
//        manually. Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter
//        is automatically chosen to convert the Greeting instance to JSON.

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
