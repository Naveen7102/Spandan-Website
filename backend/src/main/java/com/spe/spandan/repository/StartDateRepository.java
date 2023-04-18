package com.spe.spandan.repository;

import com.spe.spandan.model.StartDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface StartDateRepository extends JpaRepository<StartDate,Integer> {
    @Query(value = "SELECT * from start_date s", nativeQuery = true)
    StartDate getDate();
}
