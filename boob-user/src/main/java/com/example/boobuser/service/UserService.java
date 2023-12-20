package com.example.boobuser.service;

import com.example.boobuser.dto.UserLoginDTO;
import com.example.boobuser.dto.UserRegisterDTO;
import com.example.boobuser.model.User;

public interface UserService {

    // 注册返回id
    Long save(UserRegisterDTO param);

    Long login(UserLoginDTO param);

    int permission(UserLoginDTO param);

    int roleid(UserLoginDTO param);

}
