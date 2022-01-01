/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starkindustriesne.danceagent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mstark
 */
@Entity
@Table(name = "availability")
public class Availability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long availabilityId = 0L;

    @Column(name = "user_id", length = 30)
    @JsonIgnore
    private String userId;

    @Column(name = "dancer_name", length = 100)
    private String dancerName;
    
    @Column(name = "location", length = 75)
    private String location;
    
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm a")
    private Date startTime;
    
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm a")
    private Date endTime;
    
    @Column(name = "dance_limit")
    private int danceLimit = -1;

    @Column(name = "created_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm a")
    private Date created;

    /**
     * @return the availabilityId
     */
    public Long getAvailabilityId() {
        return availabilityId;
    }

    /**
     * @param availabilityId the availabilityId to set
     */
    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the danceLimit
     */
    public int getDanceLimit() {
        return danceLimit;
    }

    /**
     * @param danceLimit the danceLimit to set
     */
    public void setDanceLimit(int danceLimit) {
        this.danceLimit = danceLimit;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDancerName() {
        return dancerName;
    }

    public void setDancerName(String dancerName) {
        this.dancerName = dancerName;
    }
}
