package com.leonrv.hrapirest.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;

import com.leonrv.hrapirest.models.Contract;
import com.leonrv.hrapirest.models.ContractType;
import com.leonrv.hrapirest.models.Employee;
@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeDto {
    

    private String FullName;
    private String TaxIdNumber;
    private String Email;
    private String nameContractType;
    private Date DateFrom;
    private Date DateTo;
    private Double SalaryPerDay;

    public static List<EmployeeDto> employeesToDtos(List<Employee> employees){

        List<EmployeeDto> employeeDtos = null;

        try {
            employeeDtos = new ArrayList<>();
            // employees.forEach( e -> {
            //     employeeDtos.add(new EmployeeDto(
            //         e.getName() + " " + e.getLastName(),
            //         e.getTaxIdNumber(),
            //         e.getEmail(),
            //         EmployeeDto.getActiveContract(e).getContractType().getName(),
            //         EmployeeDto.getActiveContract(e).getDateFrom(),
            //         EmployeeDto.getActiveContract(e).getDateTo(),
            //         EmployeeDto.getActiveContract(e).getSalaryPerDay()
            //     ));
            // });

            for(Employee e : employees){
                
                if(EmployeeDto.getActiveContract(e) != null) {
                    employeeDtos.add(new EmployeeDto(
                        e.getName() + " " + e.getLastName(),
                        e.getTaxIdNumber(),
                        e.getEmail(),
                        EmployeeDto.getActiveContract(e).getContractType().getName(),
                        EmployeeDto.getActiveContract(e).getDateFrom(),
                        EmployeeDto.getActiveContract(e).getDateTo(),
                        EmployeeDto.getActiveContract(e).getSalaryPerDay()
                    ));
                }else{
                    employeeDtos.add(new EmployeeDto(
                        e.getName() + " " + e.getLastName(),
                        e.getTaxIdNumber(),
                        e.getEmail(),
                        null,
                        null,
                        null,
                        null
                    ));
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return employeeDtos;
    }

    public static Contract getActiveContract(Employee employee){
        Contract contract = null;
        try {
            // employee.getContracts().forEach( c -> {
            //     if(c.getIsActive()){
            //         contract = c;
            //     }
            // });

            for(Contract c : employee.getContracts()){
                if(c.getIsActive()){
                    contract = c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return contract;
        
    }
}
