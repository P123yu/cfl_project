package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findByEmpId(Long empId);
}
