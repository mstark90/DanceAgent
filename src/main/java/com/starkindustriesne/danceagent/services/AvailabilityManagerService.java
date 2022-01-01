package com.starkindustriesne.danceagent.services;

import com.starkindustriesne.danceagent.NotFoundException;
import com.starkindustriesne.danceagent.domain.Availability;
import com.starkindustriesne.danceagent.dto.CreateAvailabilityRequest;
import com.starkindustriesne.danceagent.repositories.AvailabilityRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AvailabilityManagerService {
    private static final Logger LOGGER = Logger.getLogger(AvailabilityManagerService.class.getName());

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityManagerService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    private Jwt getUser() {
        return (Jwt)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    public Availability create(CreateAvailabilityRequest request) {
        Jwt user = getUser();

        Availability availability = new Availability();

        availability.setUserId(user.getClaimAsString("uid"));
        availability.setDancerName(user.getClaimAsString("name"));
        availability.setLocation(request.getLocation());
        availability.setStartTime(request.getStartTime());
        availability.setEndTime(request.getEndTime());
        availability.setDanceLimit(request.getDanceLimit());
        availability.setCreated(new Date());

        availability = this.availabilityRepository.save(availability);

        return availability;
    }

    public List<Availability> findByLocation(String location) {
        return this.availabilityRepository.findAllByLocation(location);
    }

    public List<Availability> findByDancerName(String dancerName) {
        return this.availabilityRepository.findAllByDancerName(dancerName);
    }

    public List<Availability> findByCurrentUser() {
        Jwt user = getUser();

        return this.findByUserId(user.getClaimAsString("uid"));
    }

    public List<Availability> findByUserId(String userId) {
        return this.availabilityRepository.findAllByUserId(userId);
    }

    public Availability findById(Long availabilityId) {
        Optional<Availability> result =
                this.availabilityRepository.findByAvailabilityId(availabilityId);

        if(result.isEmpty()) {
            throw new NotFoundException();
        }

        return result.get();
    }

    public List<Availability> findCurrent() {
        return this.availabilityRepository.findAllByStartTimeAfter(new Date());
    }
}
