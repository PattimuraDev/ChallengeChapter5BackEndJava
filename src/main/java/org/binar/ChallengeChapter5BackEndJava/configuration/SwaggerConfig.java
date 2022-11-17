package org.binar.ChallengeChapter5BackEndJava.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Method untuk mengubah tampilan swagger ui
     * @param appDescription deskripsi dari dokumentasi API swagger
     * @param appVersion     version dari dokumentasi API swagger
     * @return
     */
    @Bean
    public OpenAPI demoApi(
            @Value("Demo REST API Challenge Binar Academy") String appDescription,
            @Value("v1.0.0") String appVersion
    ) {
        return new OpenAPI().info(new Info()
                .title("Patra API")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")
                )
        );
    }
}

