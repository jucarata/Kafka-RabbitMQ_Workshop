package com.gimnasio.apigateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            String auth = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (auth != null) {
                return chain.filter(
                        exchange.mutate()
                                .request(req.mutate()
                                        .header(HttpHeaders.AUTHORIZATION, auth)
                                        .build())
                                .build()
                );
            }
            return chain.filter(exchange);
        };
    }

}