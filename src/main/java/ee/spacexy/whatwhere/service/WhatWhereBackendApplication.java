package ee.spacexy.whatwhere.service;

import ee.spacexy.whatwhere.service.config.ApplicationProperties;
import ee.spacexy.whatwhere.service.utils.DefaultProfileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableConfigurationProperties(ApplicationProperties.class)
@Slf4j
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "ee.spacexy.whatwhere.service.web.api.AuthApi")})
public class WhatWhereBackendApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WhatWhereBackendApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    protected static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("""

                 ----------------------------------------------------------
                 Application '{}' is running! Access URLs:
                 Local: \t\t{}://localhost:{}{}
                 External: \t{}://{}:{}{}
                 Profile(s): \t{}
                 ----------------------------------------------------------
                """,
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());
    }
}
