package com.tourguide.domain.dto;

import java.util.List;

public class TravelSchedule {
    private String destination;
    private List<String> scheduleItems;

    public TravelSchedule(String destination, List<String> scheduleItems) {
        this.destination = destination;
        this.scheduleItems = scheduleItems;
    }

    public String getDestination() {
        return destination;
    }

    public List<String> getScheduleItems() {
        return scheduleItems;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setScheduleItems(List<String> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }
}

