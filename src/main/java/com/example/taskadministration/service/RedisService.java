package com.example.taskadministration.service;

import com.example.taskadministration.dto.request.TaskDue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, TaskDue taskDue, long timeoutInSeconds) {
//        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(timeoutInSeconds));
        redisTemplate.opsForHash().put("task:"+ key, "taskName",taskDue.getTaskName());
        redisTemplate.opsForHash().put("task:"+ key, "dueTime",taskDue.getLocalDateTime());
    }
}
