package com.example.taskadministration.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemRequest {
    private Long id;
    @NotBlank
    private String itemName;
    @NotNull
    @NotEmpty
    private List<ItemDetailRequest> itemDetailRequestList;

    public ItemRequest(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public List<ItemDetailRequest> getItemDetailRequestList() {
        return itemDetailRequestList;
    }
    public void setItemDetailRequestList(List<ItemDetailRequest> itemDetailRequestList) {
        this.itemDetailRequestList = itemDetailRequestList;
    }
}
