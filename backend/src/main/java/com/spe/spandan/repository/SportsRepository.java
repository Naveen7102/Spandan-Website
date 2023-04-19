package com.spe.spandan.repository;

import com.spe.spandan.model.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface SportsRepository extends JpaRepository<Sports,Integer> {
    @Query(value = "SELECT s.id from sports s where s.name = :sport ", nativeQuery = true)
    Integer getSportId(@Param("sport") String sport);

    @Query(value = "SELECT s.name from sports s where s.id = :id ", nativeQuery = true)
    String getSportName(@Param("id") Integer id);

    @Query(value = "SELECT * from sports s", nativeQuery = true)
    ArrayList<Sports> getSports();
}
