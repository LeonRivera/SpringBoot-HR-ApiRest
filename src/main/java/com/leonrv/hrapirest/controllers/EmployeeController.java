package com.leonrv.hrapirest.controllers;

import org.springframework.web.bind.annotation.*;
import com.leonrv.hrapirest.models.*;
import com.leonrv.hrapirest.repositories.*;

@RestController @RequestMapping("/api/v1/employee") @CrossOrigin("*")
public class EmployeeController extends GenericController<Employee, Integer> {
    public EmployeeController(IGenericRepository<Employee, Integer> repository) {
        super(repository);
    }
}
