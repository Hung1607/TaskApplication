package com.example.taskadministration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class RedisSchedule {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRate = 1000)
    public List<Map<String, String>> getTasksDueSoon() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = now.plusMinutes(5);

        List<Map<String, String>> dueTasks = new ArrayList<>();

        Set<String> taskKeys = redisTemplate.keys("task:*");
        if (taskKeys == null || taskKeys.isEmpty()) {
            return dueTasks;
        }

        for (String key : taskKeys) {

            Object dueTimeStr = redisTemplate.opsForHash().get(key, "dueTime");

            if (dueTimeStr != null) {
                LocalDateTime dueTime = LocalDateTime.parse(
                        dueTimeStr.toString().replace("\"", "")
                );

                if (dueTime.isAfter(now) && dueTime.isBefore(limit)) {
                    Map<Object, Object> hashEntries = redisTemplate.opsForHash().entries(key);

                    Map<String, String> taskDetails = new HashMap<>();
                    taskDetails.put("key", key);
                    taskDetails.put("taskName", (String) hashEntries.get("taskName"));
                    taskDetails.put("dueTime", (String) hashEntries.get("dueTime"));
                    dueTasks.add(taskDetails);

                    redisTemplate.delete(key);
                }
            }
        }
        return dueTasks;
    }
}
