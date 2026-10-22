package com.example.taskadministration.dto.request;

import com.example.taskadministration.model.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class TaskRequest {
    private Long id;
    @NotBlank
    private String taskName;
    @NotBlank
    private String information;
    private LocalDateTime deadline;
    @NotNull
    @NotEmpty
    private List<ItemRequest> itemRequestsList;
    private Status status;

    public TaskRequest(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public List<ItemRequest> getItemRequestsList() {
        return itemRequestsList;
    }
    public void setItemRequestsList(List<ItemRequest> itemRequestsList) {
        this.itemRequestsList = itemRequestsList;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
