package com.example.taskadministration.service;

import com.example.taskadministration.dto.request.ItemRequest;
import com.example.taskadministration.error.BadRequestException;
import com.example.taskadministration.model.ItemDetailMapper;
import com.example.taskadministration.model.TaskMapper;
import com.example.taskadministration.dto.request.TaskRequest;
import com.example.taskadministration.dto.response.TaskResponse;
import com.example.taskadministration.model.entity.ItemEntity;
import com.example.taskadministration.model.entity.TaskEntity;
import com.example.taskadministration.model.enums.Status;
import com.example.taskadministration.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ItemDetailMapper itemDetailMapper;
    private final KafkaProducerService kafkaProducerService;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper,
                       ItemDetailMapper itemDetailMapper,
                       KafkaProducerService kafkaProducerService){
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.itemDetailMapper = itemDetailMapper;
        this.kafkaProducerService = kafkaProducerService;
    }

    public TaskResponse createTask(TaskRequest taskRequest){
        TaskEntity task = taskMapper.toTaskEntity(taskRequest);
        task.setStatus(Status.CREATED);
        TaskEntity taskEntity = this.taskRepository.save(task);
        kafkaProducerService.sendMessage(taskRequest);
        return this.taskMapper.toTaskResponse(taskEntity);
    }

    public TaskResponse addTask(TaskRequest taskRequest){
        TaskEntity task = taskMapper.toTaskEntity(taskRequest);
        task.setStatus(Status.CREATED);
        TaskEntity taskEntity = this.taskRepository.save(task);
        kafkaProducerService.sendMessage(taskRequest);
        return this.taskMapper.toTaskResponse(taskEntity);
    }

    public List<TaskResponse> getAllTask(){
        List<TaskEntity> taskEntityList = this.taskRepository.findAll();
        return taskEntityList.stream().map(this.taskMapper::toTaskResponse).toList();
    }

    public TaskResponse getTaskById(Long id){
        Optional<TaskEntity> taskEntity = this.taskRepository.findById(id);
        TaskResponse taskResponse = null;
        if(taskEntity.isPresent()){
            taskResponse = taskMapper.toTaskResponse(taskEntity.get());
        }
        return taskResponse;
    }

    public TaskResponse moreTask(TaskRequest taskRequest){
        TaskEntity task = taskMapper.toTaskEntity(taskRequest);
        task.setStatus(Status.CREATED);
        TaskEntity taskEntity = this.taskRepository.save(task);
        kafkaProducerService.sendMessage(taskRequest);
        return this.taskMapper.toTaskResponse(taskEntity);
    }

    public TaskResponse updateTask(TaskRequest taskRequest){
        TaskEntity taskEntity = taskRepository.findById(taskRequest.getId())
                .orElseThrow(BadRequestException::new);
        taskEntity.setTaskName(taskRequest.getTaskName());
        taskEntity.setInformation(taskRequest.getInformation());
        List<ItemRequest> itemRequestsList = taskRequest.getItemRequestsList();
        List<ItemEntity> itemEntityList = taskEntity.getItemEntityList();
        for(ItemRequest itemRequest : itemRequestsList){
            for(ItemEntity itemEntity : itemEntityList){
                if(Objects.equals(itemRequest.getId(), itemEntity.getId())){
                    itemEntity.setItemName(itemEntity.getItemName());
                    itemEntity.setItemDetailJsonList(itemRequest.getItemDetailRequestList()
                            .stream()
                            .map(this.itemDetailMapper::toItemDetailJON).toList());
                }
            }
        }
        taskEntity.setItemEntityList(itemEntityList);
        TaskEntity task = taskRepository.save(taskEntity);
        return taskMapper.toTaskResponse(task);
    }

}
