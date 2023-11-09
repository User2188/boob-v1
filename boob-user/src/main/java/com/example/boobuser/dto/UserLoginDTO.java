package com.example.boobuser.dto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserLoginDTO implements Serializable {
    private String username;
    private String password;
}
