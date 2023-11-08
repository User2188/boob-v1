package com.example.boobuser.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobuser.dto.UserLoginDTO;
import com.example.boobuser.dto.UserRegisterDTO;
import com.example.boobuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/login")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ServerResponseEntity<String> register(UserLoginDTO param) {
        Long type = userService.login(param);
        if (type.equals(0L)){
            return ServerResponseEntity.showFailMsg("user not exits.");
        }
        else if(type.equals(1L)){
            return ServerResponseEntity.showFailMsg("password wrong.");
        }
        else if(type.equals(2L)){
            return ServerResponseEntity.success("login success!");
        }
        else{
            return ServerResponseEntity.showFailMsg("unknown error.");
        }
    }
}
