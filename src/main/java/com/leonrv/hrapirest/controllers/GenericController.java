package com.leonrv.hrapirest.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.leonrv.hrapirest.repositories.*;
import com.leonrv.hrapirest.services.*;

public abstract class GenericController<T, TID> {

    GenericService<T, TID> service;

    public GenericController(IGenericRepository<T, TID> repository){
        this.service = new GenericService<T,TID>(repository) {};
    }

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable TID id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody T updated){
        return ResponseEntity.ok(service.save(updated));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody T created){
        return ResponseEntity.ok(service.save(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable TID id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }

}
