package hello;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*By including the Spring Mobile dependency, Spring Boot configures a `DeviceResolverHandlerInterceptor`
and `DeviceHandlerMethodArgumentResolver` automatically. `DeviceResolverHandlerInterceptor` examines
the `User-Agent` header in the incoming request, and based on the header value, determines whether
the request is coming from a normal (desktop) browser, a mobile (phone) browser, or a tablet browser.
The `DeviceHandlerMethodArgumentResolver` allows Spring MVC to use the resolved Device object in a
controller method.*/
@Controller
public class DeviceDetectionController {

    /*The @ResponseBody annotation tells Spring MVC to write the returned
    object into the response body, rather than to render a model into a view.*/
    @RequestMapping("/detect-device")
    public @ResponseBody String detectDevice(Device device) {
        String deviceType = "unknown";
        if (device.isNormal()) {
            deviceType = "normal";
        } else if (device.isMobile()) {
            deviceType = "mobile";
        } else if (device.isTablet()) {
            deviceType = "tablet";
        }
        return "Hello " + deviceType + " browser!";
    }

}
