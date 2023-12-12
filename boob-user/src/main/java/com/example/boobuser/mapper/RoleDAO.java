package com.example.boobuser.mapper;

import com.example.boobuser.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role,Integer> {
}
