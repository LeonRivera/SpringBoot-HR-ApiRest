package com.leonrv.hrapirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonrv.hrapirest.models.Employee;
import com.leonrv.hrapirest.repositories.IEmployeeRepository;
import com.leonrv.hrapirest.repositories.IGenericRepository;

@Service
public class EmployeeService extends GenericService<Employee, Integer>{


    public EmployeeService(IGenericRepository<Employee, Integer> repository) {
        super(repository);
    }


    @Autowired(required = false)
    IEmployeeRepository repository;
    

    public Employee findByTaxIdNumber(String taxIdNumber){
        return repository.findFirstByTaxIdNumber(taxIdNumber);
    }


    
}
