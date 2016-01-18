package tutorial;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static public void main(String[] args) throws Throwable {
        new AnnotationConfigApplicationContext(
        		Main.class.getPackage().getName());
    }
}
