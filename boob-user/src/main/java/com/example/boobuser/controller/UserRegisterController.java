package com.example.boobuser.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobuser.dto.UserLoginDTO;
import com.example.boobuser.dto.UserRegisterDTO;
import com.example.boobuser.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/test")
    public ServerResponseEntity<Long> register_test(UserRegisterDTO param) {
        log.info("user:"+param.getUsername(),"password:"+param.getPassword());
        if(param.getUsername() == null || param.getPassword() == null){
            return ServerResponseEntity.showFailMsg("error! null exits.");
        }

        Long id = userService.save(param);
        log.info("save return:"+id);
        if (id == 0L){
            return ServerResponseEntity.showFailMsg("error! user exits.");
        }
        else{

            return ServerResponseEntity.success(id);
        }
    }

    @PostMapping("/register")
    public ServerResponseEntity<Long> register(@RequestBody String payload) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserRegisterDTO param = objectMapper.readValue(payload, UserRegisterDTO.class);
        log.info("user:"+param.getUsername(),"password:"+param.getPassword());
        if(param.getUsername() == null || param.getPassword() == null){
            return ServerResponseEntity.showFailMsg("error! null exits.");
        }

        Long id = userService.save(param);
        log.info("save return:"+id);
        if (id == 0L){
            return ServerResponseEntity.showFailMsg("error! user exits.");
        }
        else{

            return ServerResponseEntity.success(id);
        }
    }

}
