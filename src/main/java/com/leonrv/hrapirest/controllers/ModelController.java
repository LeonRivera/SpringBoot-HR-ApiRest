package com.leonrv.hrapirest.controllers;

import org.springframework.web.bind.annotation.*;
import com.leonrv.hrapirest.models.*;
import com.leonrv.hrapirest.repositories.*;

@RestController @RequestMapping("/api/model") @CrossOrigin("*")
public class ModelController extends GenericController<Model> {
    public ModelController(IGenericRepository<Model> repository) {
        super(repository);
    }
}
