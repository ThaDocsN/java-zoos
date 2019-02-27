package com.example.zoo.repos;

import com.example.zoo.models.Zoos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ZooRepository extends JpaRepository<Zoos, Long> {
    public Optional<Zoos> findByZooName(String zooName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zoo_animal WHERE zooid = :zooid", nativeQuery = true)
    void deleteZoo(long zooid);
}