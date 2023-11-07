package ee.spacexy.whatwhere.service.config.security;

import ee.spacexy.whatwhere.service.security.CustomGrantedAuthoritiesMapper;
import ee.spacexy.whatwhere.service.security.CustomJwtAuthenticationConverter;
import ee.spacexy.whatwhere.service.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import java.util.Arrays;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {

    private final SecurityProblemSupport problemSupport;
    private final String oauth2IssuerUri;
    private final UserService userService;

    public SecurityConfiguration(SecurityProblemSupport problemSupport, @Value("${security.oauth2.issuer-uri:}") String oauth2IssuerUri, UserService userService) {
        this.problemSupport = problemSupport;
        this.oauth2IssuerUri = oauth2IssuerUri;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(
                new NegatedRequestMatcher(new OrRequestMatcher(
                    antMatcher("/docs/**"),
                    antMatcher("/swagger-ui/**"),
                    antMatcher(HttpMethod.OPTIONS, "/**")
                )))
            .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .cors(securityCorsConfigurer -> securityCorsConfigurer.configurationSource(corsConfigurationSource()))
            .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(problemSupport).authenticationEntryPoint(problemSupport))
            .headers(headerSpec -> headerSpec
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                .contentSecurityPolicy(contentSecurityPolicySpec -> contentSecurityPolicySpec.policyDirectives("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:; connect-src 'self' " + oauth2IssuerUri + "/"))
                .referrerPolicy(referrerPolicySpec -> referrerPolicySpec.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                .permissionsPolicy(permissionsPolicySpec -> permissionsPolicySpec.policy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'"))
            )
            .authorizeHttpRequests(authorization -> authorization
                .requestMatchers("/").permitAll()
                .requestMatchers("/*.*").permitAll()
                .requestMatchers("/docs").permitAll()
                .requestMatchers("/api/logout").authenticated()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/swagger-resources/**", "/v2/api-docs/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/management/health").permitAll()
                .requestMatchers("/management/info").permitAll()
                .requestMatchers("/management/**").hasAuthority("ROLE_ADMIN")
            )
            .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        return new CustomJwtAuthenticationConverter(userService);
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return new CustomGrantedAuthoritiesMapper();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
