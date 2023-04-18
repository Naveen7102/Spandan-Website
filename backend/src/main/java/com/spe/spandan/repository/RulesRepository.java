package com.spe.spandan.repository;

import com.spe.spandan.model.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RulesRepository extends JpaRepository<Rules,Integer>{
    @Query(value = "SELECT * from rules r", nativeQuery = true)
    Rules getRule();
}

