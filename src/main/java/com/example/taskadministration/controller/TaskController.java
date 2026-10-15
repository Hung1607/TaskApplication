package com.example.taskadministration.controller;


import com.example.taskadministration.dto.request.TaskRequest;
import com.example.taskadministration.dto.response.TaskResponse;
//import com.example.taskadministration.repository.UserRepository;
import com.example.taskadministration.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

//    private AuthenticationManager authenticationManager;

//    private JwtGenerator jwtGenerator;

    public TaskController(TaskService taskAdminService
//                          UserRepository userRepository
//                          PasswordEncoder passwordEncoder,
//                          AuthenticationManager authenticationManager,
//                          JwtGenerator jwtGenerator
    ){
        this.taskService = taskAdminService;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
            TaskResponse taskResponse = this.taskService.createTask(taskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping("/getAllTask")
    public ResponseEntity<List<TaskResponse>> getAllTask(){
        return ResponseEntity.ok(this.taskService.getAllTask());
    }

    @GetMapping("/getTask")
    public ResponseEntity<TaskResponse> getById(@RequestParam(required = true, name = "value") Long taskValue){
        return ResponseEntity.ok(this.taskService.getTaskById(taskValue));
    }

    @PutMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@Valid @RequestBody TaskRequest taskRequest)
    {
        return ResponseEntity.ok(this.taskService.updateTask(taskRequest));
    }

//    @PostMapping("/registerUser")
//    public ResponseEntity<String> createUser(@RequestBody Register register){
//        UserEntity userEntity = new UserEntity();
//
//        userEntity.setName(register.getUsername());
//        userEntity.setPassword(this.passwordEncoder.encode(register.getPassword()));
////        userEntity.setRole(register.getRole());
//
//        this.userRepository.save(userEntity);
//
//        return ResponseEntity.ok("User created");
//    }

//    @PostMapping("/loginUser")
//    public ResponseEntity<String> loginUser(@RequestBody Register register){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(register.getUsername(),register.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = this.jwtGenerator.generateToken(authentication);
//
//        return ResponseEntity.ok("Bearer " + token);
//    }


}
