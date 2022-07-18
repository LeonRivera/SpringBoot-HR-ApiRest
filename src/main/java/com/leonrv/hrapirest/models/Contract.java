package com.leonrv.hrapirest.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity @Data @NoArgsConstructor
public class Contract implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ContractId",columnDefinition = "BIGINT")
    private Long ContractId;

    // private Integer EmployeeId;
    // private Integer ContractTypeId;
    
    @Column(nullable = false)
    private Date DateFrom;

    @Column(nullable = false)
    private Date DateTo;

    @Column(nullable = false)
    private Double SalaryPerDay;

    @Column(nullable = false)
    private Boolean IsActive;
    
    @Column(nullable = false)
    private Date DateCreated;

    @ManyToOne
    @JoinColumn(name = "EmployeeId")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "ContractTypeId")
    private ContractType contractType;
}
