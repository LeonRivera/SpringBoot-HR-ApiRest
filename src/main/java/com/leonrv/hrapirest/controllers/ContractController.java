package com.leonrv.hrapirest.controllers;
import org.springframework.web.bind.annotation.*;

import com.leonrv.hrapirest.dtos.EmployeeDto;
import com.leonrv.hrapirest.models.*;
import com.leonrv.hrapirest.repositories.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import com.leonrv.hrapirest.services.*;
import com.leonrv.hrapirest.utils.*;

@RestController
@RequestMapping("/api/v1/contract")
@CrossOrigin("*")
public class ContractController {


    GenericService<Employee, Integer> serviceEmployee;
    GenericService<Contract, Long> serviceContract;

    public ContractController(IGenericRepository<Employee, Integer> repositoryEmployee,
    IGenericRepository<Contract, Long> repositoryContract) {
        this.serviceEmployee = new GenericService<Employee, Integer>(repositoryEmployee) {
        };
        this.serviceContract = new GenericService<Contract, Long>(repositoryContract) {
        };
    }
    
}
