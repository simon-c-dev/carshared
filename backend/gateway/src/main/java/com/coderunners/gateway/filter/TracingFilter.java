package com.coderunners.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TracingFilter extends AbstractGatewayFilterFactory<Object> {

    private static final Logger logger = LoggerFactory.getLogger(TracingFilter.class);

    private static final String CUSTOM_HEADER_NAME = "X-Gateway-Processed";
    private static final String CUSTOM_HEADER_VALUE = "true";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            logger.info("Processing request: {}", exchange.getRequest().getURI());

            exchange.getRequest().mutate()
                    .headers(httpHeaders -> httpHeaders.add(CUSTOM_HEADER_NAME, CUSTOM_HEADER_VALUE));

            return chain.filter(exchange).then(Mono.fromRunnable(() -> logger.info("Processed request successfully by the API-GATEWAY: {}", exchange.getRequest().getURI())));
        };
    }
}
