package ee.spacexy.whatwhere.service.config;

import ee.spacexy.whatwhere.service.web.rest.errors.ExceptionTranslator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(ExceptionTranslator.class)
@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final ApplicationProperties applicationProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
                .allowedOrigins(applicationProperties.getCors().getAllowedOrigins())
                .allowedMethods(applicationProperties.getCors().getAllowedMethods())
                .allowedHeaders(applicationProperties.getCors().getAllowedHeaders())
                .maxAge(applicationProperties.getCors().getMaxAge());
    }
}
