package com.example.zoo.repos;

import com.example.zoo.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    public Optional<Animal> findByAnimaltype(String type);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zoo_animal WHERE animalid = :animalid", nativeQuery = true)
    void deleteAnimalFromZooAnimal(long animalid);
}