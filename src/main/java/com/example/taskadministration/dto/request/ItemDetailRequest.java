package com.example.taskadministration.dto.request;

import java.time.LocalTime;

public class ItemDetailRequest {
    private String location;
    private LocalTime timeLimit;
    public ItemDetailRequest(){}

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalTime getTimeLimit() {
        return timeLimit;
    }
    public void setTimeLimit(LocalTime timeLimit) {
        this.timeLimit = timeLimit;
    }
}
