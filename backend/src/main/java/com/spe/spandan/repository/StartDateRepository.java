package com.spe.spandan.repository;

import com.spe.spandan.model.StartDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface StartDateRepository extends JpaRepository<StartDate,Integer> {
    @Query(value = "SELECT * from start_date s limit 1", nativeQuery = true)
    StartDate getDate();

    @Modifying
    @Query(value = "UPDATE start_date s SET s.date = :date", nativeQuery = true)
    void updateDate(@Param("date") String date);
}
