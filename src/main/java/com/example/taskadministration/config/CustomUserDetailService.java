//package com.example.taskadministration.config;
//
//import com.example.taskadministration.model.entity.UserEntity;
//import com.example.taskadministration.repository.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//    public UserDetails loadUserByUsername(String username) {
//        UserEntity userEntity = this.userRepository.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("Username not found")
//        );
//        return new User(userEntity.getName(),
//                userEntity.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority("something")));
//    }
//}
