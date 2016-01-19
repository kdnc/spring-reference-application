package tutorial.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tutorial.Cat;
import tutorial.Dog;

public class Main {
    public static void main(String[] args) throws Throwable {

         ApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AnimalFarmConfig.class);

        Dog dog = annotationConfigApplicationContext.getBean(Dog.class);
        dog.bark();

        Cat cat = annotationConfigApplicationContext.getBean(Cat.class);
        cat.meow();
    }
}
