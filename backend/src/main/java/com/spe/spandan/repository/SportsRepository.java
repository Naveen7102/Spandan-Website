package com.spe.spandan.repository;

import com.spe.spandan.model.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SportsRepository extends JpaRepository<Sports,Integer> {
    @Query(value = "SELECT s.id from sports s where s.name = :sport ", nativeQuery = true)
    Integer getSportId(@Param("sport") String sport);

    @Query(value = "SELECT s.name from sports s where s.id = :id ", nativeQuery = true)
    String getSportName(@Param("id") Integer id);
}
