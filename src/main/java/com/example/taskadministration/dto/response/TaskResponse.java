package com.example.taskadministration.dto.response;

import com.example.taskadministration.model.enums.Status;

import java.util.List;

public class TaskResponse {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String taskName;
    private String information;
    private Status status;

    private List<ItemResponse> itemResponseList;
    public String getTaskName() {
        return taskName;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
    public List<ItemResponse> getItemResponseList() {
        return itemResponseList;
    }

    public void setItemResponseList(List<ItemResponse> itemResponseList) {
        this.itemResponseList = itemResponseList;
    }


}
