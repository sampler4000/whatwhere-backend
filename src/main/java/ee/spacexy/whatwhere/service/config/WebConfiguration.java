package ee.spacexy.whatwhere.service.config;

import ee.spacexy.whatwhere.service.web.rest.errors.ExceptionTranslator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(ExceptionTranslator.class)
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${whatwhere.cors.allowed-origins}")
    private String[] allowedOrigins;
    @Value("${whatwhere.cors.allowed-methods}")
    private String[] allowedMethods;
    @Value("${whatwhere.cors.allowed-headers}")
    private String[] allowedHeaders;
    @Value("${whatwhere.cors.exposed-headers}")
    private String[] exposedHeaders;
    @Value("${whatwhere.cors.allow-credentials}")
    private boolean allowCredentials;
    @Value("${whatwhere.cors.max-age}")
    private long maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .exposedHeaders(exposedHeaders)
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }
}
