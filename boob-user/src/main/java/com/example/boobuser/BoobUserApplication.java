package com.example.boobuser;

import com.example.boobuser.mapper.PermissionDAO;
import com.example.boobuser.mapper.RoleDAO;
import com.example.boobuser.model.Permission;
import com.example.boobuser.model.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.boobuser", "com.example.boobjwt"})
public class BoobUserApplication {

    public static void main(String[] args) {
        var ctx=SpringApplication.run(BoobUserApplication.class, args);

        System.out.println("插入");
        var role=ctx.getBean(RoleDAO.class);
        var permission=ctx.getBean(PermissionDAO.class);
        var permission1=new Permission(1,"删除自己帖子",List.of());
        var permission2=new Permission(2,"删除别人帖子",List.of());
        permission.save(permission1);
        permission.save(permission2);
        role.save(new Role(1,"普通用户", List.of(permission1)));
        role.save(new Role(2,"管理员", List.of(permission1,permission2)));
        System.out.println("完成");

    }

}
