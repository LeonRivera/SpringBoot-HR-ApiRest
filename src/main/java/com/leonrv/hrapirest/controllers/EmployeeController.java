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

// public class EmployeeController extends GenericController<Employee, Integer> {
//     public EmployeeController(IGenericRepository<Employee, Integer> repository) {
//         super(repository);
//     }
@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
public class EmployeeController {

    GenericService<Employee, Integer> service;
    GenericService<Contract, Long> serviceContract;

    public EmployeeController(IGenericRepository<Employee, Integer> repository,
    IGenericRepository<Contract, Long> repositoryContract) {
        this.service = new GenericService<Employee, Integer>(repository) {
        };
        this.serviceContract = new GenericService<Contract, Long>(repositoryContract) {
        };
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        if(ControllerUtils.validateAccess(headers)){
            List<EmployeeDto> employeeDtos = EmployeeDto.employeesToDtos(service.findAll());
            return ResponseEntity.ok(employeeDtos);
        }else{
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }
        
    }

    @GetMapping("/all")
    public List<Employee> findAllEmployees(@RequestHeader Map<String, String> headers){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee, 
    @RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        if(ControllerUtils.validateAccess(headers)){
            if (EmployeeUtils.validateRfc(employee.getTaxIdNumber())) {
                try {
                    return ResponseEntity.ok(service.save(employee));
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                System.out.println("Fallo la validacion");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }else{
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Employee employee,
    @RequestHeader Map<String, String> headers){
        Map<String, Object> responseMap = new HashMap<>();

        if(ControllerUtils.validateAccess(headers)){

            if(EmployeeUtils.validateRfc(employee.getTaxIdNumber())){
                return ResponseEntity.ok(service.save(employee));
            }else{
                responseMap.put("error", "RFC (TaxIdNumber) Incorrect Format");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }

    }


    @DeleteMapping("/disablecontractemployee/{id}")
    public ResponseEntity<?> disableValidContract(@PathVariable Integer id ,@RequestHeader Map<String, String> headers) {

        Map<String, Object> responseMap = new HashMap<>();

        if(ControllerUtils.validateAccess(headers)){

            // Buscar al empleado por su id
            Employee employee = service.getById(id);

            // Buscar contrato vigente del empleado
            Contract contract = EmployeeUtils.getActiveContract(employee);

            //obteniendo del contexto
            contract = serviceContract.getById(contract.getContractId());

            // Actualizar el DateTo de su contrato 

            if(contract!=null){
                contract.setDateTo(new Date());

                //actualizando
                serviceContract.save(contract);

                return ResponseEntity.ok(service.getById(id));

            }else{
                responseMap.put("error", "This employee doesn't have active contracts");
                 return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
                // error
            }

            // Actualizar empleado

           

           
        }else{
            responseMap.put("error", "User or password incorrect");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.UNAUTHORIZED);
        }
        
    }
}
