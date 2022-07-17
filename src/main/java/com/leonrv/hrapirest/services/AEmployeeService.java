package com.leonrv.hrapirest.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.leonrv.hrapirest.models.Employee;
import com.leonrv.hrapirest.repositories.IEmployeeRepository;
import com.leonrv.hrapirest.repositories.IGenericRepository;

public abstract class AEmployeeService extends GenericService<Employee, Integer>{


    public AEmployeeService(IGenericRepository<Employee, Integer> repository) {
        super(repository);
    }


    @Autowired
    IEmployeeRepository repository;
    

    public Employee findByTaxIdNumber(String taxIdNumber){
        return repository.findByTaxIdNumber(taxIdNumber);
    }


    
}
