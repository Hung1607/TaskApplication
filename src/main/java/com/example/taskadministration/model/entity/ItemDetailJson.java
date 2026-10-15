package com.example.taskadministration.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetailJson {
    private String location;
    private LocalTime timeLimit;
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
