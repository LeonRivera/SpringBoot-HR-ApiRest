package com.leonrv.hrapirest.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
public class Employee implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EmployeeId;

    private String TaxIdNumber;
    private String Name;
    private String LastName;
    private Date BirthDate;
    private String Email;
    private String CellPhone;
    private Boolean IsActive;
    private Date DateCreated;

    @OneToMany
    @JoinColumn(name = "EmployeeId")
    private List<Contract> contracts;
}
