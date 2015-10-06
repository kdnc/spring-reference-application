package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    /*The @ResponseBody annotation tells Spring MVC to write the returned
    object into the response body, rather than to render a model into a view.*/
    @RequestMapping("/")
    public @ResponseBody
    String main() {
//        A RESTful service has been stood up at http://gturnquist-quoters.cfapps.io/api/random.
//        It randomly fetches quotes about Spring Boot and returns them as a JSON document.

//        RestTemplate makes interacting with most RESTful services a one-line incantation.
//        And it can even bind that data to custom domain types.
        RestTemplate restTemplate = new RestTemplate();

//        Because the Jackson JSON processing library is in the classpath,
//        `RestTemplate` will use it (via a message converter) to convert the incoming JSON data into a `Quote` object.
//        Here you’ve only used `RestTemplate` to make an HTTP `GET` request.
//        But `RestTemplate` also supports other HTTP verbs such as `POST`, `PUT`, and `DELETE`.
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote.toString();
    }
}
