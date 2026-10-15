package com.example.taskadministration.dto.response;

import java.time.LocalTime;

public class ItemDetailResponse {
//    private Long id;
    private String location;
    private LocalTime timeLimit;

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
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
