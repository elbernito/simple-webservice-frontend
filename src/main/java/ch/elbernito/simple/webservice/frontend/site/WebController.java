package ch.elbernito.simple.webservice.frontend.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.elbernito.simple.webservice.frontend.config.ServerConfig;
import ch.elbernito.simple.webservice.frontend.service.DataService;
import ch.elbernito.simple.webservice.library.AppVersion;
import ch.elbernito.simple.webservice.library.Version;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebController {

    private final ServerConfig serverConfig;
    private final DataService dataService;

    public WebController(final ServerConfig serverConfig, final DataService dataService){
        this.serverConfig = serverConfig;
        this.dataService = dataService;
    }


    @RequestMapping(value = "/")
    public String index(final Model model) {
        log.info("index model called");

        final AppVersion appVersion = AppVersion.create(serverConfig.getMavenPomLocation());

        model.addAttribute("localId", appVersion.getVersionModel().getId());
        model.addAttribute("localGroupId", appVersion.getVersionModel().getGroupId());
        model.addAttribute("localArtifactId", appVersion.getVersionModel().getArtifactId());
        model.addAttribute("localVersion", appVersion.getVersionModel().getVersion());

        if(dataService.getExternalData().isPresent()){
            final Version externalVersion = dataService.getExternalData().get();
            model.addAttribute("externalId", externalVersion.getId());
            model.addAttribute("externalGroupId", externalVersion.getGroupId());
            model.addAttribute("externalArtifactId", externalVersion.getArtifactId());
            model.addAttribute("externalVersion", externalVersion.getVersion());
        } else {
            model.addAttribute("externalId", "NoData");
            model.addAttribute("externalGroupId", "NoData");
            model.addAttribute("externalArtifactId", "NoData");
            model.addAttribute("externalVersion", "NoData");
        }
        return "index";
    }

}