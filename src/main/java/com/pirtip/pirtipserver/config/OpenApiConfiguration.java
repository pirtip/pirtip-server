package com.pirtip.pirtipserver.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
	servers = {
		@Server(url = "https://yeop2.world/"),
		@Server(url = "http://localhost:8080/"),
		@Server(url = "http://192.168.0.41:8080/")
	}
)
public class OpenApiConfiguration {
}
