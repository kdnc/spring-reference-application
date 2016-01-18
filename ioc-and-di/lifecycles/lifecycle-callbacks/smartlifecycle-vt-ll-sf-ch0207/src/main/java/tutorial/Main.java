package tutorial;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Throwable {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Astronaut.class.getPackage().getName());
        applicationContext.registerShutdownHook();
    }
}
