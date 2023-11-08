package com.example.boobuser.mapper;

import com.example.boobuser.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByName(@Param("name") String name);

    void save(@Param("name") String name, @Param("password") String password);

    Long findIdByName(@Param("name") String name);

    String findPasswordByName(@Param("name") String name);
}
