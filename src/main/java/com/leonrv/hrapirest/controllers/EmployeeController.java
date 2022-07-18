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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// public class EmployeeController extends GenericController<Employee, Integer> {
//     public EmployeeController(IGenericRepository<Employee, Integer> repository) {
//         super(repository);
//     }
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
@Tag(description = "Employee Operations API Rest", name = "Employee Controller")
public class EmployeeController {

    // GenericService<Employee, Integer> service;
    // GenericService<Contract, Long> serviceContract;

    @Autowired
    ContractService contractService;

    @Autowired
    EmployeeService employeeService;

    // public EmployeeController(
    // ) {
    // IGenericRepository<Employee, Integer> repository = new
    // IGenericRepository<Employee,Integer>() {
    // };

    // IGenericRepository<Contract, Long> repositoryContract = new
    // IGenericRepository<Contract,Long>() {

    // };

    // this.service = new GenericService<Employee, Integer>(repository) {
    // };
    // this.serviceContract = new GenericService<Contract, Long>(repositoryContract)
    // {
    // };
    // }

    @GetMapping
    @Operation(summary = "List of all employees")
    @ApiResponse(responseCode = "401", description = "User or password incorrect")
    @ApiResponse(responseCode = "500", description = "Error")
    public ResponseEntity<?> findAll(@RequestHeader Map<String, String> headers) {

        System.out.println(headers);

        Map<String, Object> responseMap = new HashMap<>();

        if (ControllerUtils.validateAccess(headers)) {
            List<EmployeeDto> employeeDtos = EmployeeDto.employeesToDtos(employeeService.findAll());
            return ResponseEntity.ok(employeeDtos);
        } else {
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }

    }

    // @GetMapping("/all")
    // public List<Employee> findAllEmployees(@RequestHeader Map<String, String>
    // headers){
    // return employeeService.findAll();
    // }

    // @GetMapping("/rfc/{rfc}")
    // public ResponseEntity<?> findByRfc(@PathVariable String rfc){
    // System.out.println(rfc);
    // return ResponseEntity.ok(employeeService.findByTaxIdNumber(rfc));
    // }

    @PostMapping
    @Operation(summary = "Save a Employee")
    @ApiResponse(responseCode = "401", description = "User or password incorrect")
    @ApiResponse(responseCode = "500", description = "Error")
    public ResponseEntity<?> save(
            @RequestBody Employee employee,
            @RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        try {
            if (ControllerUtils.validateAccess(headers)) {
                if (EmployeeUtils.validateRfc(employee.getTaxIdNumber())) {
                    try {
                        return ResponseEntity.ok(employeeService.save(employee));
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                } else {
                    System.out.println("Fallo la validacion");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                responseMap.put("error", "User or password incorrect");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping
    @Operation(summary = "Update a selected employee")
    @ApiResponse(responseCode = "401", description = "User or password incorrect")
    @ApiResponse(responseCode = "500", description = "Error")
    public ResponseEntity<?> update(@RequestBody Employee employee,
            @RequestHeader Map<String, String> headers) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            if (ControllerUtils.validateAccess(headers)) {

                if (EmployeeUtils.validateRfc(employee.getTaxIdNumber())) {
                    return ResponseEntity.ok(employeeService.save(employee));
                } else {
                    responseMap.put("error", "RFC (TaxIdNumber) Incorrect Format");
                    return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                responseMap.put("error", "User or password incorrect");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/disablecontract/{id}")
    @Operation(summary = "Disable the valid contract of a selected employee")
    @ApiResponse(responseCode = "401", description = "User or password incorrect")
    @ApiResponse(responseCode = "500", description = "Error")
    public ResponseEntity<?> disableValidContract(@PathVariable Integer id,
            @RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        try {

            if (ControllerUtils.validateAccess(headers)) {

                Employee employee = employeeService.getById(id);

                Contract contract = EmployeeUtils.getActiveContract(employee);

                contract = contractService.getById(contract.getContractId());

                if (contract != null) {
                    contract.setDateTo(new Date());
                    contract.setIsActive(false);

                    contractService.save(contract);

                    return ResponseEntity.ok(employeeService.getById(id));

                } else {
                    responseMap.put("error", "This employee doesn't have active contracts");
                    return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } else {
                responseMap.put("error", "User or password incorrect");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }
}
