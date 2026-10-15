package com.example.taskadministration.service;

import com.example.taskadministration.dto.request.ItemDetailRequest;
import com.example.taskadministration.dto.request.ItemRequest;
import com.example.taskadministration.dto.request.TaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(value = "dev")
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskName("3 Yoghurt");
        taskRequest.setInformation("asd");

        ItemRequest itemRequest1 = new ItemRequest();
        itemRequest1.setItemName("yoghurt");
        List<ItemDetailRequest> itemDetailRequestList = new ArrayList<>();
        ItemDetailRequest itemDetailRequest = new ItemDetailRequest();
        itemDetailRequest.setLocation("Somewhere safe");
        itemDetailRequest.setTimeLimit(LocalTime.of(10,00,00));
        itemDetailRequestList.add(itemDetailRequest);
        itemRequest1.setItemDetailRequestList(itemDetailRequestList);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/task/createTask")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(taskRequest))
                )
                .andExpect(status().isCreated());
    }
}
