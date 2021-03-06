/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starkindustriesne.danceagent.repositories;

import com.starkindustriesne.danceagent.domain.Availability;
import com.starkindustriesne.danceagent.domain.DanceRequest;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mstark
 */
@Repository
public interface DanceRequestRepository extends CrudRepository<DanceRequest, Long>,
        JpaSpecificationExecutor<DanceRequest> {
    List<DanceRequest> findByAvailability(Availability avail);

    List<DanceRequest> findAllByAvailabilityIn(Collection<Availability> availabilities);

}
