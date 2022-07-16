package com.leonrv.hrapirest.services;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import com.leonrv.hrapirest.repositories.*;;

// @Service
public abstract class GenericService<T> {

    // @Autowired
    IGenericRepository<T> repository;

    public GenericService(IGenericRepository<T> repository){
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T getById(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    public T save(T entity){
        return repository.save(entity);
    }

    public void delete(Long id){
        if(getById(id)!=null){
            repository.deleteById(id);
        }
    }

}
