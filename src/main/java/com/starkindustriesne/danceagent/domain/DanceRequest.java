/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starkindustriesne.danceagent.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "dance_requests")
public class DanceRequest implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dance_request_id")
    private long danceRequestId = 0L;
    
    @ManyToOne
    @JoinColumn(name = "availability_id", referencedColumnName = "availability_id")
    private Availability availability;

    @Column(name = "requestor_name", length = 255)
    private String name;
    
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Column(name="dance_status")
    @Enumerated(EnumType.ORDINAL)
    private DanceStatus status;

    /**
     * @return the danceRequestId
     */
    public long getDanceRequestId() {
        return danceRequestId;
    }

    /**
     * @param danceRequestId the danceRequestId to set
     */
    public void setDanceRequestId(long danceRequestId) {
        this.danceRequestId = danceRequestId;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the status
     */
    public DanceStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(DanceStatus status) {
        this.status = status;
    }
}
