package com.example.zoo.controllers;

import com.example.zoo.models.Animal;
import com.example.zoo.repos.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/animals/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimalController {
    @Autowired
    AnimalRepository animalRepo;

    @GetMapping("/animals")
    public List<Animal> getAllAnimals(){
        List<Animal> list = animalRepo.findAll();
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @GetMapping("/animals/{type}")
    public Animal getAnimalByType(@PathVariable String type){
        var found = animalRepo.findByAnimaltype(type);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }
}