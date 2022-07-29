package server.mayfill.controller.log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HealthCheckLoggingController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

}
