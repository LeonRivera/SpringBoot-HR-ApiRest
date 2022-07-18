package com.leonrv.hrapirest.services;

import org.springframework.stereotype.Service;

import com.leonrv.hrapirest.models.*;
import com.leonrv.hrapirest.repositories.IGenericRepository;

@Service
public class ContractService extends GenericService<Contract, Long>{
    public ContractService(IGenericRepository<Contract, Long> repository) {
        super(repository);
    }
}
