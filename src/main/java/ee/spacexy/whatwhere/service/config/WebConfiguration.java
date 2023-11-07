package ee.spacexy.whatwhere.service.config;

import ee.spacexy.whatwhere.service.web.rest.errors.ExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(ExceptionTranslator.class)
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("https://whatwhere-admin.codo.ee/")
            .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
