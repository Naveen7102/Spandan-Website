package com.spe.spandan.repository;

import com.spe.spandan.model.Fixtures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FixturesRepository extends JpaRepository<Fixtures,Integer> {
    @Query(value = "SELECT * from fixtures f where f.sport_id = :sport_id order by f.time", nativeQuery = true)
    List<Fixtures> getFixturesbySport(@Param("sport_id") Integer sport_id);

    @Modifying
    @Query(value = "UPDATE fixtures f SET f.result = :result, f.winner = :winner WHERE f.id = :id", nativeQuery = true)
    void updateResult(@Param("result") String result, @Param("winner") Integer winner, @Param("id") Integer id);
}
