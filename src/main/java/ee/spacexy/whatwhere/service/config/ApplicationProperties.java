package ee.spacexy.whatwhere.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

}
