package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

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
/*`@EnableScheduling` ensures that a background task executor is created. Without it, nothing
 gets scheduled.
*/
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
//        The `main()` method uses Spring Boot’s `SpringApplication.run()` method to launch an application.
//        Did you notice that there wasn’t a single line of XML? No web.xml file either. This web
//        application is 100% pure Java
        SpringApplication.run(Application.class, args);
    }
}
