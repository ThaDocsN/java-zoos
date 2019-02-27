package com.example.zoo.controllers;

import com.example.zoo.models.Animal;
import com.example.zoo.models.Telephone;
import com.example.zoo.models.ZooAnimal;
import com.example.zoo.models.Zoos;
import com.example.zoo.repos.AnimalRepository;
import com.example.zoo.repos.TelephoneRepository;
import com.example.zoo.repos.ZooAnimalRepository;
import com.example.zoo.repos.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Admin {

    @Autowired
    ZooRepository zooRepo;

    @Autowired
    TelephoneRepository telephoneRepo;

    @Autowired
    AnimalRepository animalRepo;

    @Autowired
    ZooAnimalRepository zooAnimalRepo;

    @PutMapping("/zoos/{zooid}")
    public Zoos updateZooById(@RequestBody Zoos newZoo, @PathVariable long zooid) throws URISyntaxException {
        Optional<Zoos> updateZoo = zooRepo.findById(zooid);
        if (updateZoo.isPresent()) {
            newZoo.setZooid(zooid);
            zooRepo.save(newZoo);
            return newZoo;
        }
        return null;
    }

    @PutMapping("/phones/{phoneid}")
    public Telephone updateTelephoneById(@RequestBody Telephone newPhone, @PathVariable long phoneid) throws URISyntaxException {
        Optional<Telephone> telephoneToUpdate = telephoneRepo.findById(phoneid);
        if (telephoneToUpdate.isPresent()) {
            newPhone.setPhoneid(phoneid);
            telephoneRepo.save(newPhone);
            return newPhone;
        }
        return null;
    }

    @PutMapping("/animals/{animalid}")
    public Animal updateAnimalById(@RequestBody Animal newAnimal, @PathVariable long animalid) throws URISyntaxException {
        Optional<Animal> updateAnimal = animalRepo.findById(animalid);
        if (updateAnimal.isPresent()) {
            newAnimal.setAnimalid(animalid);
            animalRepo.save(newAnimal);
            return newAnimal;
        }
        return null;
    }

    @PostMapping("/zoos")
    public Zoos addZoo(@RequestBody Zoos zoo) {
        return zooRepo.save(zoo);
    }

    @PostMapping("/phones")
    public Telephone addPhone(@RequestBody Telephone number) {
        return telephoneRepo.save(number);
    }

    @PostMapping("/animals")
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalRepo.save(animal);
    }

    @PostMapping("/zoos/animals")
    public ZooAnimal addAnimalZooRelation(@RequestBody ZooAnimal zooAnimal) {
        return zooAnimalRepo.save(zooAnimal);
    }

    @DeleteMapping("/zoos/{zooid}")
    public Zoos deleteZooById(@PathVariable long zooid) {
        var foundZoo = zooRepo.findById(zooid);
        if (foundZoo.isPresent()) {

            zooRepo.deleteById(zooid);
            zooRepo.deleteZoo(zooid);
            return foundZoo.get();
        }
        return null;
    }

    @DeleteMapping("/phones/{phoneid}")
    public Telephone deletePhoneById(@PathVariable long phoneid) {
        var foundPhone = telephoneRepo.findById(phoneid);
        if (foundPhone.isPresent()) {
            telephoneRepo.deleteById(phoneid);
            return foundPhone.get();
        }
        return null;
    }

    @DeleteMapping("/animals/{animalid}")
    public Animal deleteAnimalById(@PathVariable long animalid) {
        var foundAnimal = animalRepo.findById(animalid);
        if (foundAnimal.isPresent()) {
            animalRepo.deleteById(animalid);
            animalRepo.deleteAnimalFromZooAnimal(animalid);
            return foundAnimal.get();
        }
        return null;
    }

    @DeleteMapping("/zoos/{zooid}/animals/{animalid}")
    public String deleteZooAnimalRelation(@PathVariable("zooid") long zooid, @PathVariable("animalid") long animalid){
        zooAnimalRepo.deleteRelationAnimal(zooid, animalid);
        return "Deleted relation: zooid - " + zooid + " animalid - " + animalid;
    }
}