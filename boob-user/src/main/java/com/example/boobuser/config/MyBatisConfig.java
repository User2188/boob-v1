package com.example.boobuser.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.boobuser.mapper")
public class MyBatisConfig {

}
