package com.example.boobuser;

import com.example.boobuser.mapper.UserMapper;
import com.example.boobuser.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoobUserApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testUserController() throws Exception {
//        // insert一条数据，并select出来验证
//        userMapper.save("hhh", "123456");
//        User u = userMapper.findByName("hhh");
//        System.out.println(u.getPassword());
//
//        // 查找不存在的，返回null
//        u = userMapper.findByName("456");
//        System.out.println(u == null);

        //用name查找id，可返回Long
        Long id = userMapper.findIdByName("hhh");
        System.out.println(id);

//
//        // update一条数据，并select出来验证
//        u.setEmail("dob@163.com");
//        userMapper.update(u);
//        u = userMapper.findByName("wws");
//
//        // 删除这条数据，并select验证
//        userMapper.delete(u.getId());
//        u = userMapper.findByName("wws");


    }

}
