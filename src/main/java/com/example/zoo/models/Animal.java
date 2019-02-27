package com.example.zoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long animalid;

    private String animaltype;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "zooid")
    @JsonIgnoreProperties("animals")
    private Set<Zoos> zoos = new HashSet<>();

    public Animal() {
    }

    public long getAnimalid() {
        return animalid;
    }

    public void setAnimalid(long animalid) {
        this.animalid = animalid;
    }

    public String getAnimaltype() {
        return animaltype;
    }

    public void setAnimaltype(String animaltype) {
        this.animaltype = animaltype;
    }

    public Set<Zoos> getZoos() {
        return zoos;
    }

    public void setZoos(Set<Zoos> zoos) {
        this.zoos = zoos;
    }
}