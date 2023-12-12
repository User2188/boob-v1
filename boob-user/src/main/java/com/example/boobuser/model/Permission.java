package com.example.boobuser.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Permission {
    @Id
    int id;

    String name;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "role_permission_rel",
            joinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    List<Role> roles;
}
