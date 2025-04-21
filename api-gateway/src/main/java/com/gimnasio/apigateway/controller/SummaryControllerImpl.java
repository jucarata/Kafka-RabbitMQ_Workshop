package com.gimnasio.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
public class SummaryControllerImpl implements SummaryController {

    private final Builder webClientBuilder;

    @Value("${service.uri.classes}") private String classesURI;
    @Value("${service.uri.equipment}")  private String equipmentURI;
    @Value("${service.uri.member}")  private String memberURI;
    @Value("${service.uri.trainer}")  private String trainerURI;

    @Autowired
    public SummaryControllerImpl(Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    @GetMapping("/total")
    public Mono<ResponseEntity<Map<String, Object>>> getTotalSummary(ServerHttpRequest request) {

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        WebClient webClient = webClientBuilder.build();

        Mono<Object> classes = fetchData(webClient, classesURI, authHeader, "classes");
        Mono<Object> trainers = fetchData(webClient, trainerURI, authHeader, "trainers");
        Mono<Object> members = fetchData(webClient, memberURI, authHeader, "members");
        Mono<Object> equipments = fetchData(webClient, equipmentURI, authHeader, "equipments");

        return Mono.zipDelayError(classes, trainers, members, equipments).map(tuple -> {
            Map<String, Object> result = new HashMap<>();
            if (tuple.getT1() != null) result.put("classes", tuple.getT1());
            if (tuple.getT2() != null) result.put("trainers", tuple.getT2());
            if (tuple.getT3() != null) result.put("members", tuple.getT3());
            if (tuple.getT4() != null) result.put("equipments", tuple.getT4());
            return ResponseEntity.ok(result);
        });
    }

    private Mono<Object> fetchData(WebClient webClient, String uri, String authHeader, String label) {
        return webClient.get()
                .uri(uri)
                .headers(headers -> headers.set(HttpHeaders.AUTHORIZATION, authHeader))
                .retrieve()
                .bodyToMono(Object.class)
                .onErrorResume(e -> {
                    Map<String, String> errorInfo = new HashMap<>();
                    errorInfo.put("error", "No autorizado");
                    return Mono.just(errorInfo);
                });
    }

}
