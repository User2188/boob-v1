package com.example.boobuser.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class User {
    private Long  id;
    private String username;
    private String email;
    private String password;
    private Date create;
    private int pic;
    private int roleid;
}
