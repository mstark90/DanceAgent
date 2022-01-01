/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starkindustriesne.danceagent.repositories;

import com.starkindustriesne.danceagent.domain.Availability;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mstark
 */
@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
    Optional<Availability> findByAvailabilityId(Long availabilityId);

    List<Availability> findAllByUserId(String userId);

    List<Availability> findAllByDancerName(String dancerName);

    List<Availability> findAllByLocation(String location);

    List<Availability> findAllByStartTimeAfter(Date startTime);
}
