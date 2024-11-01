package com.coderunners.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<Object> {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            logger.info("Incoming request to {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());

            // Here, we can add custom headers, modify requests, etc.
            // For now, let's simply pass the request through
            return chain.filter(exchange).then(Mono.fromRunnable(() -> logger.info("Outgoing response with status code {}", exchange.getResponse().getStatusCode())));
        };
    }
}
