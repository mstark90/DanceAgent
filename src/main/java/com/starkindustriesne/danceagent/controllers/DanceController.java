package com.starkindustriesne.danceagent.controllers;

import com.starkindustriesne.danceagent.domain.DanceRequest;
import com.starkindustriesne.danceagent.domain.DanceSpecification;
import com.starkindustriesne.danceagent.services.DanceManagerService;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dances")
public class DanceController {

    private static final Logger LOGGER = Logger.getLogger(DanceController.class.getName());

    private final DanceManagerService danceManagerService;

    public DanceController(DanceManagerService danceManagerService) {
        this.danceManagerService = danceManagerService;
    }

    @GetMapping
    public List<DanceRequest> getForCurrentUser() {
        return this.danceManagerService.getForCurrentUser();
    }

    @GetMapping("/search")
    public List<DanceRequest> search(@RequestParam(name = "query") String query) {
        return this.danceManagerService.search(query);
    }

    @PostMapping("/{danceId}")
    public DanceRequest updateDance(@PathVariable Long danceId, @RequestBody DanceRequest request) {
        return this.danceManagerService.update(danceId, request);
    }
}
