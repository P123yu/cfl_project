package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.CflRoles;
import com.cfl.cfl_project.repository.CflRolesRepository;
import com.cfl.cfl_project.service.CflRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CflRolesServiceImpl implements CflRolesService {

    @Autowired
    private CflRolesRepository cflRolesRepository;

    @Override
    public CflRoles create(CflRoles cflRoles) {
        CflRoles cflRolesObj=new CflRoles();
        cflRolesObj.setRoleName(cflRoles.getRoleName());
        Long year= (long) LocalDate.now().getYear();
        cflRolesObj.setYear(year);
        return cflRolesRepository.save(cflRolesObj);
    }

    @Override
    public CflRoles getCflRoleByYear(Long year) {
        return cflRolesRepository.findByYear(year);
    }
}
