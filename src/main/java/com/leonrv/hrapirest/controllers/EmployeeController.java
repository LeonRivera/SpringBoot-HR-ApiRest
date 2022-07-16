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


// public class EmployeeController extends GenericController<Employee, Integer> {
//     public EmployeeController(IGenericRepository<Employee, Integer> repository) {
//         super(repository);
//     }
@RestController @RequestMapping("/api/v1/employee") @CrossOrigin("*")
public class EmployeeController{

    GenericService<Employee, Integer> service;

    public EmployeeController(IGenericRepository<Employee, Integer> repository) {
        this.service = new GenericService<Employee, Integer>(repository) {};
    }

    // @Override
    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> findAllE(){
        List<EmployeeDto> employeeDtos = EmployeeDto.employeesToDtos(service.findAll());
        return ResponseEntity.ok(employeeDtos);
    }
}
