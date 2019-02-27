package com.example.zoo.repos;

import com.example.zoo.models.ZooAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ZooAnimalRepository extends JpaRepository<ZooAnimal, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zoo_animal WHERE zooid = :zooid AND animalid = :animalid", nativeQuery = true)
    void deleteRelationAnimal(long zooid, long animalid);
}