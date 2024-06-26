package motion.programming.users.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Users API")
                        .version("1.0")
                        .license(new License()
                                .name("Motion Programming 1.0")
//                                .url("http://www.apache.org/licenses/LICENSE-2.0")
                        ));
    }
}
