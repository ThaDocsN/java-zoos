package com.example.zoo.controllers;

import com.example.zoo.models.Zoos;
import com.example.zoo.repos.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/zoos/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZooController {
    @Autowired
    ZooRepository zooRepo;

    @GetMapping("/zoos")
    public List<Zoos> getAllZoos(){
        List<Zoos> list = zooRepo.findAll();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @GetMapping("/zoos/{name}")
    public Zoos getZooByName(@PathVariable String name){
        var found = zooRepo.findByZooname(name);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }
}