package com.example.boobuser.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobuser.dto.UserRegisterDTO;
import com.example.boobuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/register")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ServerResponseEntity<Long> register(UserRegisterDTO param) {
        Long id = userService.save(param);
        if (id == 0L){
            return ServerResponseEntity.showFailMsg("error! user exits.");
        }
        else{

            return ServerResponseEntity.success(id);
        }
    }

}
