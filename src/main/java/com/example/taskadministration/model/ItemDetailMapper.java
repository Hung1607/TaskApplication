package com.example.taskadministration.model;

import com.example.taskadministration.dto.request.ItemDetailRequest;
import com.example.taskadministration.dto.response.ItemDetailResponse;
import com.example.taskadministration.model.entity.ItemDetailJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemDetailMapper {
    ItemDetailJson toItemDetailJON(ItemDetailRequest itemDetailRequest);

    ItemDetailResponse toItemDetailResponse(ItemDetailJson ItemDetailJson);
}
