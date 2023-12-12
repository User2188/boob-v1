package com.example.boobuser.mapper;

import com.example.boobuser.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDAO extends JpaRepository<Permission,Integer> {
}
