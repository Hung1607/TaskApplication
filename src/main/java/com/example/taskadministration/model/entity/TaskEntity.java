package com.example.taskadministration.model.entity;

import com.example.taskadministration.model.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "information")
    private String information;
    @Column(name = "status")
    private Status status;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "taskEntity",
            fetch = FetchType.LAZY)
    private List<ItemEntity> itemEntityList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public List<ItemEntity> getItemEntityList(){
        return this.itemEntityList;
    }
    public void setItemEntityList(List<ItemEntity> itemEntityList){
        this.itemEntityList = itemEntityList;
    }
    public String getTaskName(){
        return this.taskName;
    }
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
