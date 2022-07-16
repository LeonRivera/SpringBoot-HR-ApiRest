package com.leonrv.hrapirest.models;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
public class ContractType implements Serializable{
    static final long serialVersionUID = 3L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ContractTypeId;

    private String Name;
    private String Description;
    private Boolean IsActive;
    private Date DateCreated;

    @OneToMany
    @JoinColumn(name = "ContractTypeId")
    // @JsonIgnore
    private List<Contract> contracts;
}
