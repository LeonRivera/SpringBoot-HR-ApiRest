package com.leonrv.hrapirest.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity @Data @NoArgsConstructor
public class Contract implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ContractId;

    // private Integer EmployeeId;
    // private Integer ContractTypeId;
    
    
    private Date DateFrom;
    private Date DateTo;
    private Double SalaryPerDay;
    private Boolean IsActive;
    private Date DateCreated;


}
