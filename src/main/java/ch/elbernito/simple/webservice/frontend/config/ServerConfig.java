package ch.elbernito.simple.webservice.frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Getter
public class ServerConfig {

    @Value("${server.frontend.address}")
    private String serverFrontendAddress;

    @Value("${server.frontend.port}")
    private String serverFrontendPort;

    @Value("${server.backend.address}")
    private String serverBackendAddress;

    @Value("${server.backend.port}")
    private String serverBackendPort;

    @Value("${server.maven.pom.location}")
    private String mavenPomLocation;

}
