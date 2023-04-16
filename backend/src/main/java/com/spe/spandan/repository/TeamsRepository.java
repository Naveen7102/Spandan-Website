package com.spe.spandan.repository;

import com.spe.spandan.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamsRepository extends JpaRepository<Teams,Integer>{
    @Query(value = "SELECT t.name from teams t where t.id = :id ", nativeQuery = true)
    String getTeamName(@Param("id") Integer id);
}
