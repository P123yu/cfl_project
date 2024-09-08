package com.cfl.cfl_project.repository;


import com.cfl.cfl_project.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register,Long> {
    Register findByUserName(String username);
    Boolean existsByUserName(String username);
}
