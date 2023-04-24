package ch.elbernito.simple.webservice.frontend.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ch.elbernito.simple.webservice.frontend.config.ServerConfig;
import ch.elbernito.simple.webservice.library.Version;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataService {

    private final ServerConfig serverConfig;

    public DataService(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public Optional<Version> getExternalData() {
        log.info("Get external backend data called");
        final RestTemplate restTemplate = new RestTemplate();
        final String url = String.format("http://%s:%s/version", serverConfig.getServerBackendAddress(), serverConfig.getServerBackendPort());
        try {
            final ResponseEntity<Version>   response = restTemplate.getForEntity(url, Version.class);
            if(response.getStatusCode().is2xxSuccessful()){
                return Optional.of(response.getBody());
            }
        } catch (RestClientException e) {
            log.error("Failure while get data from external service.", e);
        }
        return Optional.empty();

    }

}
