package com.gimnasio.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface SummaryController {
    Mono<ResponseEntity<Map<String, Object>>> getTotalSummary(ServerHttpRequest request);
}
