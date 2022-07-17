package com.leonrv.hrapirest.repositories;

import org.springframework.stereotype.Repository;

import com.leonrv.hrapirest.models.*;

@Repository
public interface IEmployeeRepository extends IGenericRepository<Employee, Integer>{
    Employee findByTaxIdNumber(String taxIdNumber);
}

