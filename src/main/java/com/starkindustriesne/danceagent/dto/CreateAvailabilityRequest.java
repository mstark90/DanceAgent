package com.starkindustriesne.danceagent.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CreateAvailabilityRequest {
    @NotNull
    @Size(min = 10, max = 75)
    private String location;

    @NotNull
    private Date startTime;

    @NotNull
    private Date endTime;

    @NotNull
    private int danceLimit = -1;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDanceLimit() {
        return danceLimit;
    }

    public void setDanceLimit(int danceLimit) {
        this.danceLimit = danceLimit;
    }
}
