package com.coderunners.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter extends AbstractGatewayFilterFactory<Object> {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitingFilter.class);

    private static final int MAX_REQUESTS = 30; // a bit high for testing purpose
    private static final Duration REFRESH_INTERVAL = Duration.ofMinutes(1);

    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

    public RateLimitingFilter() {
        super(Object.class);

        // Start a periodic task to reset the counters
        Flux.interval(REFRESH_INTERVAL)
                .doOnNext(tick -> counters.clear())
                .subscribe();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
            AtomicInteger counter = counters.computeIfAbsent(ip, k -> new AtomicInteger(0));

            if (counter.get() >= MAX_REQUESTS) {
                logger.warn("Rate limit exceeded for IP: {}", ip);
                return onError(exchange);
            }

            logger.info("Remaining requests from IP: {} out of {}", ip, MAX_REQUESTS - counter.incrementAndGet() + 1);
            return chain.filter(exchange)
                    .doOnTerminate(() -> logger.info("Processed request successfully: {}", exchange.getRequest().getURI()));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        byte[] bytes = "Rate limit exceeded".getBytes();
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }
}
