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


    @PostMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody Contract contract,
    @PathVariable Integer id, 
    @RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        if(ControllerUtils.validateAccess(headers)){

            Employee employee=serviceEmployee.getById(id);
            if(employee!=null){
                contract.setEmployee(employee);
                return ResponseEntity.ok(serviceContract.save(contract));
            }else{
                responseMap.put("error", "Employee doesn't exist");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
            }

        }else{
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }
    }



    
}
