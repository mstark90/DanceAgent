package com.starkindustriesne.danceagent.controllers;

import com.starkindustriesne.danceagent.domain.Availability;
import com.starkindustriesne.danceagent.domain.DanceRequest;
import com.starkindustriesne.danceagent.dto.BookDanceRequest;
import com.starkindustriesne.danceagent.dto.CreateAvailabilityRequest;
import com.starkindustriesne.danceagent.services.AvailabilityManagerService;
import com.starkindustriesne.danceagent.services.DanceManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    private final AvailabilityManagerService availabilityManagerService;
    private final DanceManagerService danceManagerService;

    public AvailabilityController(AvailabilityManagerService availabilityManagerService,
                                  DanceManagerService danceManagerService) {
        this.availabilityManagerService = availabilityManagerService;
        this.danceManagerService = danceManagerService;
    }

    @PostMapping
    public Availability create(@RequestBody CreateAvailabilityRequest request) {
        return this.availabilityManagerService.create(request);
    }

    @GetMapping
    public List<Availability> getForCurrentUser() {
        return this.availabilityManagerService.findByCurrentUser();
    }

    @GetMapping("/current")
    public List<Availability> getCurrent() {
        return this.availabilityManagerService.findCurrent();
    }

    @GetMapping("/{availabilityId}")
    public Availability getAvailabilityForId(@PathVariable Long availabilityId) {
        return this.availabilityManagerService.findById(availabilityId);
    }

    @GetMapping("/{availabilityId}/dances")
    public List<DanceRequest> getDancesForAvailability(@PathVariable Long availabilityId) {
        return this.danceManagerService.getByAvailabilityId(availabilityId);
    }
    
    @PostMapping("/{availabilityId}/dances")
    public DanceRequest updateDance(@PathVariable Long availabilityId, @RequestBody DanceRequest request) {
        return this.danceManagerService.update(availabilityId, request);
    }

    @PostMapping("/{availabilityId}/book")
    public DanceRequest bookRequest(@PathVariable Long availabilityId, @RequestBody BookDanceRequest request) {
        request.setAvailabilityId(availabilityId);
        return this.danceManagerService.book(request);
    }
}
