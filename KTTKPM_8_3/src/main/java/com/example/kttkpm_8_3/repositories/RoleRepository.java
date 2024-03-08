package com.example.kttkpm_8_3.repositories;

import java.util.Optional;

import com.example.kttkpm_8_3.models.ERole;
import com.example.kttkpm_8_3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
