package server.mayfill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableJpaAuditing
@EnableSwagger2
@SpringBootApplication
public class MayfillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayfillApplication.class, args);
    }

}
