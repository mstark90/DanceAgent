package com.starkindustriesne.danceagent.services;

import com.starkindustriesne.danceagent.BadRequestException;
import com.starkindustriesne.danceagent.GlobalConstants;
import com.starkindustriesne.danceagent.domain.Availability;
import com.starkindustriesne.danceagent.domain.DanceRequest;
import com.starkindustriesne.danceagent.dto.BookDanceRequest;
import com.starkindustriesne.danceagent.repositories.AvailabilityRepository;
import com.starkindustriesne.danceagent.repositories.DanceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DanceManagerService {
    private static final Logger LOGGER = Logger.getLogger(DanceManagerService.class.getName());

    private final AvailabilityRepository availabilityRepository;
    private final DanceRequestRepository danceRequestRepository;

    public DanceManagerService(AvailabilityRepository availabilityRepository,
                               DanceRequestRepository danceRequestRepository) {
        this.availabilityRepository = availabilityRepository;
        this.danceRequestRepository = danceRequestRepository;
    }

    public DanceRequest book(BookDanceRequest request) {
        Optional<Availability> availabilityRequest = this.availabilityRepository.findByAvailabilityId(request.getAvailabilityId());
        Availability availability = availabilityRequest.orElse(null);

        if(availabilityRequest.isEmpty()) {
            throw new BadRequestException(GlobalConstants.NOT_AVAILABLE_MSG);
        }

        List<DanceRequest> danceRequests = this.danceRequestRepository.findByAvailability(availability);

        if(availability.getDanceLimit() > -1 && danceRequests.size() >= availability.getDanceLimit()) {
            throw new BadRequestException(GlobalConstants.DANCER_FULL_MSG);
        }

        DanceRequest danceRequest = new DanceRequest();

        danceRequest.setCreated(new Date());
        danceRequest.setName(request.getName());
        danceRequest.setAvailability(availability);

        danceRequest = this.danceRequestRepository.save(danceRequest);

        return danceRequest;
    }

    public List<DanceRequest> getByAvailabilityId(Long availabilityId) {
        Optional<Availability> availabilityRequest = this.availabilityRepository.findByAvailabilityId(availabilityId);
        Availability availability = availabilityRequest.orElse(null);

        if(availabilityRequest.isEmpty()) {
            throw new BadRequestException(GlobalConstants.NOT_AVAILABLE_MSG);
        }

        return this.danceRequestRepository.findByAvailability(availability);
    }
}
