package com.spe.spandan.repository;

import com.spe.spandan.model.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface RulesRepository extends JpaRepository<Rules,Integer>{
    @Query(value = "SELECT * from rules r where r.sport_id = :sport_id", nativeQuery = true)
    ArrayList<Rules> getRule(@Param("sport_id") Integer sport_id);
}

