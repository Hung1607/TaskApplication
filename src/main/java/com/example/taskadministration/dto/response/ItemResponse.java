package com.example.taskadministration.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemResponse {
    private Long id;
    @NotBlank
    private String itemName;
    private String testing;
    private String testing1;
    private String testing3;
    private String testing5;
    private String testing6;
    private String testing7;
    @NotNull
    @NotEmpty
    private List<ItemDetailResponse> itemDetailResponseList;

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
    public List<ItemDetailResponse> getItemDetailResponseList() {
        return itemDetailResponseList;
    }
    public void setItemDetailResponseList(List<ItemDetailResponse> itemDetailResponseList) {
        this.itemDetailResponseList = itemDetailResponseList;
    }
}