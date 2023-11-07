package ee.spacexy.whatwhere.service.config;

import ee.spacexy.whatwhere.service.web.rest.errors.ExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(ExceptionTranslator.class)
@Configuration
public class WebConfiguration {

}
