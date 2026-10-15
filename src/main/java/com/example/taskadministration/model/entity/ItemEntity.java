package com.example.taskadministration.model.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "details", columnDefinition = "jsonb")
    private List<ItemDetailJson> itemDetailJsonList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity taskEntity;

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }
    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public String getItemName(){
        return this.itemName;
    }
    public List<ItemDetailJson> getItemDetailJsonList() {
        return itemDetailJsonList;
    }
    public void setItemDetailJsonList(List<ItemDetailJson> itemDetailJsonList) {
        this.itemDetailJsonList = itemDetailJsonList;
    }

}
