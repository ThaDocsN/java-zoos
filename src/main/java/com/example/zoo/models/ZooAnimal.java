package com.example.zoo.models;

import javax.persistence.*;

@Entity
@Table(name = "zoo_animal")
public class ZooAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long zooid;

    private long animalid;

    public ZooAnimal() {
    }

    public long getZooid() {
        return zooid;
    }

    public void setZooid(long zooid) {
        this.zooid = zooid;
    }

    public long getAnimalid() {
        return animalid;
    }

    public void setAnimalid(long animalid) {
        this.animalid = animalid;
    }
}