package ch.elbernito.simple.webservice.frontend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.elbernito.simple.webservice.frontend.config.ServerConfig;
import ch.elbernito.simple.webservice.library.AppVersion;
import ch.elbernito.simple.webservice.library.Version;

@RestController
public class ApiController {

    private final ServerConfig serverConfig;

    public ApiController(final ServerConfig serverConfig){
        this.serverConfig = serverConfig;
    }


    @GetMapping("/version")
    public ResponseEntity<Version> getVersion(){
        return ResponseEntity.ok(AppVersion.create(serverConfig.getMavenPomLocation()).getVersionModel());
    }
}
