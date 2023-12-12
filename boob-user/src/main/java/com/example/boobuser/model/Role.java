package com.example.boobuser.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @ManyToMany(targetEntity = Permission.class)
    @JoinTable(name = "role_permission_rel",
                joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    List<Permission> permissions;

}
