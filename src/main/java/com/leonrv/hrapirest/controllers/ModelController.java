package com.leonrv.hrapirest.controllers;

import org.springframework.web.bind.annotation.*;
import com.leonrv.hrapirest.models.*;
import com.leonrv.hrapirest.repositories.*;

@RestController @RequestMapping("/api/v1/model") @CrossOrigin("*")
public class ModelController extends GenericController<Model, Long> {
    public ModelController(IGenericRepository<Model, Long> repository) {
        super(repository);
    }
}
