package tutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tutorial.annotation.AndroidStore;
import tutorial.annotation.IOsStore;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean(name = "ios")
    @IOsStore
    public ItunesBookShop itunesBookShop() {
        return new ItunesBookShop();
    }

    @Bean(name = "android")
    @AndroidStore
    public AmazonBookShop amazonBookShop() {
        return new AmazonBookShop();
    }
}
