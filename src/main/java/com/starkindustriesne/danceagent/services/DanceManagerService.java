package com.starkindustriesne.danceagent.services;

import com.starkindustriesne.danceagent.BadRequestException;
import com.starkindustriesne.danceagent.GlobalConstants;
import com.starkindustriesne.danceagent.NotFoundException;
import com.starkindustriesne.danceagent.domain.*;
import com.starkindustriesne.danceagent.dto.BookDanceRequest;
import com.starkindustriesne.danceagent.repositories.AvailabilityRepository;
import com.starkindustriesne.danceagent.repositories.DanceRequestRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DanceManagerService extends DanceAgentService {
    private static final Logger LOGGER = Logger.getLogger(DanceManagerService.class.getName());

    private final AvailabilityRepository availabilityRepository;
    private final DanceRequestRepository danceRequestRepository;

    public DanceManagerService(AvailabilityRepository availabilityRepository,
                               DanceRequestRepository danceRequestRepository) {
        this.availabilityRepository = availabilityRepository;
        this.danceRequestRepository = danceRequestRepository;
    }
    
    public DanceRequest update(Long danceId, DanceRequest request) {
        Optional<DanceRequest> oldRequestResp = this.danceRequestRepository.findById(danceId);

        DanceRequest oldRequest = oldRequestResp.orElse(null);

        if(oldRequestResp.isEmpty()) {
            throw new BadRequestException(GlobalConstants.NOT_AVAILABLE_MSG);
        }
        
        request.setAvailability(oldRequest.getAvailability());
        
        request = this.danceRequestRepository.save(request);
        
        return request;
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
        danceRequest.setFirstName(request.getFirstName());
        danceRequest.setLastName(request.getLastName());
        danceRequest.setEmailAddress(request.getEmailAddress());
        danceRequest.setAvailability(availability);
        danceRequest.setStatus(DanceStatus.REQUESTED);

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

    public DanceRequest getById(Long danceRequestId) {
        Optional<DanceRequest> danceRequestResp = this.danceRequestRepository.findById(danceRequestId);

        if(danceRequestResp.isEmpty()) {
            throw new NotFoundException();
        }

        return danceRequestResp.get();
    }

    public List<DanceRequest> getForCurrentUser() {
        List<Availability> availabilities = this.availabilityRepository.findAllByUserId(getCurrentUserId());

        return this.danceRequestRepository.findAllByAvailabilityIn(availabilities);
    }

    public List<DanceRequest> search(String query) {
        List<Availability> availabilities = this.availabilityRepository.findAllByUserId(getCurrentUserId());

        Specification<DanceRequest> danceSpecification = null;

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=)(\\w+?),");
        Matcher matcher = pattern.matcher(query + ",");
        while (matcher.find()) {
            DanceSearchRequestCriteria requestCriteria = new DanceSearchRequestCriteria();

            requestCriteria.setKey(matcher.group(1));
            requestCriteria.setOperation(matcher.group(2));
            requestCriteria.setValue(matcher.group(3));

            DanceSpecification newCriteria = new DanceSpecification(requestCriteria);

            if(danceSpecification == null) {
                danceSpecification = newCriteria;
            } else {
                danceSpecification = danceSpecification.and(newCriteria);
            }
        }

        return this.danceRequestRepository.findAll(danceSpecification);
    }
}
