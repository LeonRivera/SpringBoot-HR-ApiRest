package com.leonrv.hrapirest.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
public class ContractType implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short ContractTypeId;

    @Column(nullable = false, length = 80)
    private String Name;
    
    private String Description;

    @Column(nullable = false)
    private Boolean IsActive;

    @Column(nullable = false)
    private Date DateCreated;

    // @OneToMany
    // @JoinColumn(name = "ContractTypeId")
    // // @JsonIgnore
    // private List<Contract> contracts;
}
