package com.rednorte.citas.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    /**
     * @LoadBalanced permite que WebClient resuelva nombres de servicio
     * registrados en Eureka (lb://service-pacientes → IP real del servicio)
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
