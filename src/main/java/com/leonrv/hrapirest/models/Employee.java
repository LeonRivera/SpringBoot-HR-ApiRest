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

    @Column(nullable = false, length = 13)
    private String TaxIdNumber;

    @Column(nullable = false, length = 60)
    private String Name;

    @Column(nullable = false, length = 120)
    private String LastName;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date BirthDate;

    @Column(nullable = false, length = 60)
    private String Email;

    @Column(nullable = false, length = 20)
    private String CellPhone;

    @Column(nullable = false)
    private Boolean IsActive;

    @Column(nullable = false)
    private Date DateCreated;

    @OneToMany
    @JoinColumn(name = "EmployeeId")
    private List<Contract> contracts;
}
