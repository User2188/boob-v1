package com.example.boobuser.controller;

import com.example.boobcommoncore.response.ServerResponseEntity;
import com.example.boobjwt.config.JwtProperties;
import com.example.boobjwt.utils.JwtTokenUtil;
import com.example.boobuser.dto.UserLoginDTO;
import com.example.boobuser.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login/test")
    public ServerResponseEntity<?> login_test(UserLoginDTO param)  {

        Long type = userService.login(param);
        int pernum = userService.permission(param);
        if (type.equals(-1L)){
            return ServerResponseEntity.showFailMsg("user not exits." + param.getUsername() + param.getPassword());
        }
        else if(type.equals(0L)){
            return ServerResponseEntity.showFailMsg("password wrong.");
        }
        else { // 返回id,即type = id
            Map<String, Object> tokenMap = jwtTokenUtil
                    .generateTokenAndRefreshToken(String.valueOf(type),param.getUsername(),pernum);
            tokenMap.put("userId",type);
            log.info(tokenMap.get("access_token").toString());
            log.info(tokenMap.get("userId").toString());
            return ServerResponseEntity.success(tokenMap, param.getUsername() + " login success.");
        }
    }

    @PostMapping("/login")
    public ServerResponseEntity<?> login(@RequestBody String payload) throws IOException {

        log.info("login payload: {}", payload);

        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginDTO param = objectMapper.readValue(payload, UserLoginDTO.class);

        Long type = userService.login(param);
        int pernum = userService.permission(param);
        int roleid = userService.roleid(param);
        if (type.equals(-1L)){
            return ServerResponseEntity.showFailMsg("user not exits." + param.getUsername() + param.getPassword());
        }
        else if(type.equals(0L)){
            return ServerResponseEntity.showFailMsg("password wrong.");
        }
        else { // 返回id,即type = id
            Map<String, Object> tokenMap = jwtTokenUtil
                    .generateTokenAndRefreshToken(String.valueOf(type),param.getUsername(),pernum);
            tokenMap.put("userId",type);
            tokenMap.put("roleid",roleid);
            log.info(tokenMap.get("access_token").toString());
            log.info(tokenMap.get("userId").toString());
            log.info(tokenMap.get("roleid").toString());
            return ServerResponseEntity.success(tokenMap, param.getUsername() + " login success.");
        }
    }

}
