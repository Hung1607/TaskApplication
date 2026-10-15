package com.example.taskadministration.service;

import com.example.taskadministration.dto.request.TaskRequest;
import com.example.taskadministration.dto.response.TaskResponse;
import com.example.taskadministration.model.TaskMapper;
import com.example.taskadministration.model.entity.TaskEntity;
import com.example.taskadministration.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask() {
        TaskRequest taskRequest = new TaskRequest();
        TaskEntity taskEntity = new TaskEntity();

        Mockito.when(this.taskMapper.toTaskEntity(Mockito.any(TaskRequest.class)))
                .thenReturn(taskEntity);
        Mockito.when(this.taskRepository.save(Mockito.any(TaskEntity.class)))
                .thenReturn(taskEntity);

        TaskResponse taskResponse = new TaskResponse();

        Mockito.when(this.taskMapper.toTaskResponse(Mockito.any(TaskEntity.class))).thenReturn(taskResponse);

        this.taskService.createTask(taskRequest);
        Mockito.verify(this.taskRepository,Mockito.times(1)).save(Mockito.any(TaskEntity.class));
    }

    @Test
    void getTaskById(){
        TaskEntity taskEntity = new TaskEntity();
        Mockito.when(this.taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(taskEntity));

        Mockito.when(this.taskMapper.toTaskResponse(Mockito.any(TaskEntity.class))).thenReturn(new TaskResponse());

        this.taskService.getTaskById(1600L);

        Mockito.verify(this.taskRepository,Mockito.times(1)).findById(Mockito.anyLong());
    }
}