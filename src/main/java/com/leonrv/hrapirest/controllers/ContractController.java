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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/contract")
@CrossOrigin("*")
@Tag(description = "Contract Operations API Rest", name = "Contract Controller")
public class ContractController {


    @Autowired
    EmployeeService employeeService;

    GenericService<Contract, Long> serviceContract;

    public ContractController(IGenericRepository<Contract, Long> repositoryContract) {
        this.serviceContract = new GenericService<Contract, Long>(repositoryContract) {
        };
    }

    // @GetMapping
    // public List<Contract> findAll(){
    //     return serviceContract.findAll();
    // }


    @PostMapping("/{id}")
    @Operation(summary = "Save a Contract")
    @ApiResponse(responseCode = "401", description = "User or password incorrect")
    @ApiResponse(responseCode = "404", description = "Employee doesn't exist")
    @ApiResponse(responseCode = "500", description = "Error")
    public ResponseEntity<?> save(@RequestBody Contract contract,
    @PathVariable Integer id, 
    @RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            if(ControllerUtils.validateAccess(headers)){

                Employee employee=employeeService.getById(id);
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }



    
}
