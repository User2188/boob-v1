package com.example.boobuser.service.impl;

import com.example.boobuser.dto.UserLoginDTO;
import com.example.boobuser.dto.UserRegisterDTO;
import com.example.boobuser.mapper.PermissionDAO;
import com.example.boobuser.mapper.RoleDAO;
import com.example.boobuser.mapper.UserMapper;
import com.example.boobuser.model.User;
import com.example.boobuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public Long save(UserRegisterDTO param) {
        // 检验用户名是否存在
        if (userMapper.findByName(param.getUsername())!=null){
            return 0L;
        }

        // 创建用户
        userMapper.save(param.getUsername(), param.getPassword());
        Long id = userMapper.findIdByName(param.getUsername());
        return id;
    }

    @Override
    public Long login(UserLoginDTO param){
        // 检验用户名是否存在
        if (userMapper.findByName(param.getUsername())==null){
            return -1L;
        }
        else{
            User user = userMapper.findByName(param.getUsername());
            if(!user.getPassword().equals(param.getPassword())){
                return 0L;
            }
            else{
                Long id = user.getId();
                return id;
            }

        }

    }

    @Override
    public int permission(UserLoginDTO param) {
        var user=userMapper.findByName(param.getUsername());
        var roleid=user.getRoleid();
        var role=roleDAO.findById(roleid);
        int pernum=0;
        if (role.isPresent()){
            var permissions=role.get().getPermissions();

            for (var permission:permissions) {
                pernum+=Math.pow(2,permission.getId());
            }

        }
        return pernum;
    }
}
