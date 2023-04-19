package com.spe.spandan.repository;

import com.spe.spandan.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TeamsRepository extends JpaRepository<Teams,Integer>{
    @Query(value = "SELECT t.name from teams t where t.id = :id ", nativeQuery = true)
    String getTeamName(@Param("id") Integer id);

    @Query(value = "SELECT t.id from teams t where t.name = :name ", nativeQuery = true)
    Integer getTeamId(@Param("name") String name);

    @Query(value = "SELECT * from teams t where t.sport_id = :sport_id", nativeQuery = true)
    ArrayList<Teams> getTeams(@Param("sport_id") Integer sport_id);
}
