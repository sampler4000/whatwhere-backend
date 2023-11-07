package ee.spacexy.whatwhere.service.web.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServletRequestLogFilter extends OncePerRequestFilter {

    private final String[] paths = {"/api/**"};

    private final AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final String path = request.getRequestURI();

        final boolean logInfo = Arrays.stream(paths).anyMatch(p -> pathMatcher.match(p, path));
        if (logInfo) {
            log.info("Request {} start", path);
        }
        try {
            filterChain.doFilter(request, response);
            if (logInfo) {
                log.info("Request {} complete ({}ms)", path, stopWatch.getTime(TimeUnit.MILLISECONDS));
            }
        } catch (ServletException e) {
            if (logInfo) {
                log.info("Request {} failed ({}ms)", path, stopWatch.getTime(TimeUnit.MILLISECONDS));
            }
            throw e;
        }
    }
}
