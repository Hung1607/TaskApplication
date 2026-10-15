package com.example.taskadministration.model;


import com.example.taskadministration.dto.request.TaskRequest;
import com.example.taskadministration.dto.response.TaskResponse;
import com.example.taskadministration.model.entity.TaskEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface TaskMapper {

    @Mapping(target = "itemEntityList", source = "taskRequest.itemRequestsList")
    TaskEntity toTaskEntity(TaskRequest taskRequest);

    @Mapping(target = "itemResponseList", source = "taskEntity.itemEntityList")
    TaskResponse toTaskResponse(TaskEntity taskEntity);

    @AfterMapping
    default void linkTaskToItems(@MappingTarget TaskEntity taskEntity) {
        if (taskEntity.getItemEntityList() != null) {
            taskEntity.getItemEntityList().forEach(itemEntity -> itemEntity.setTaskEntity(taskEntity));
        }
    }
}