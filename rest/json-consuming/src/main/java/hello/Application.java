package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.client.RestTemplate;

/*`@SpringBootApplication` is a convenience annotation that adds all of the following:

    - `@Configuration` tags the class as a source of bean definitions for the application context.
    - `@EnableAutoConfiguration` tells Spring Boot to start adding beans based on classpath settings,
      other beans, and various property settings.
    - Normally you would add `@EnableWebMvc` for a Spring MVC app, but Spring Boot adds it automatically
      when it sees spring-webmvc on the classpath. This flags the application as a web application and
      activates key behaviors such as setting up a DispatcherServlet.
    - `@ComponentScan` tells Spring to look for other components, configurations, and services in the
      the hello package, allowing it to find the HelloController.
*/
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
//        The `main()` method uses Spring Boot’s `SpringApplication.run()` method to launch an application.
//        Did you notice that there wasn’t a single line of XML? No `web.xml` file either. This web
//        application is 100% pure Java
        SpringApplication.run(Application.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

//    @Override
//    public void run(String... strings) throws Exception {
////        A RESTful service has been stood up at http://gturnquist-quoters.cfapps.io/api/random.
////        It randomly fetches quotes about Spring Boot and returns them as a JSON document.
//
////        RestTemplate makes interacting with most RESTful services a one-line incantation.
////        And it can even bind that data to custom domain types.
//        RestTemplate restTemplate = new RestTemplate();
//
////        Because the Jackson JSON processing library is in the classpath,
////        `RestTemplate` will use it (via a message converter) to convert the incoming JSON data into a `Quote` object.
////        Here you’ve only used `RestTemplate` to make an HTTP `GET` request.
////        But `RestTemplate` also supports other HTTP verbs such as `POST`, `PUT`, and `DELETE`.
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
//    }
}