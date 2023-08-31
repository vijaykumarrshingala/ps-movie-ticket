package com.example.arv.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class PsApiGatewayApplication {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PsApiGatewayApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(PsApiGatewayApplication.class, args);
		LOGGER.info("PsApiGatewayApplication started...");
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("movie-catalog-service", r -> r.path("/api/v1/movies/**", "/api/v1/shows/**","/api/v1/theaters/**")
						.uri("lb://movie-catalog-service"))
				.route("booking-service", r -> r.path("/api/v1/bookings/**")
						.uri("lb://booking-service"))
				.route("user-service", r -> r.path("/api/v1/users/**")
						.uri("lb://user-service"))
				.build();
	}

}
