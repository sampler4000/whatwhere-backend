package ee.spacexy.whatwhere.service.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(value = "ee.spacexy.whatwhere.service.repository")
@EntityScan(basePackages = "ee.spacexy.whatwhere.service.domain")
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 1)
public class JpaConfiguration {

}
