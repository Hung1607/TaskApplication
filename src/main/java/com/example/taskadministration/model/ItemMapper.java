package com.example.taskadministration.model;

import com.example.taskadministration.dto.request.ItemRequest;
import com.example.taskadministration.dto.response.ItemResponse;
import com.example.taskadministration.model.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ItemDetailMapper.class)
public interface ItemMapper {
    @Mapping(source = "itemRequest.itemDetailRequestList", target = "itemDetailJsonList")
    ItemEntity toItemEntity(ItemRequest itemRequest);

    @Mapping(source = "itemEntity.itemDetailJsonList", target = "itemDetailResponseList")
    ItemResponse toItemResponse(ItemEntity itemEntity);
}
