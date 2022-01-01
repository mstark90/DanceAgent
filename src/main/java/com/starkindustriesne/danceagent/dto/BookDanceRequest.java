package com.starkindustriesne.danceagent.dto;

public class BookDanceRequest {
    private long availabilityId;

    private String name;

    public long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
