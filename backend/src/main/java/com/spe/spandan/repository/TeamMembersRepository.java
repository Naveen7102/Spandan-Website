package com.spe.spandan.repository;

import com.spe.spandan.model.TeamMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TeamMembersRepository extends JpaRepository<TeamMembers,Integer>{
    @Query(value = "select t.participant_id from team_members t where t.sport_id=:sport_id and t.team_id=:team_id", nativeQuery = true)
    ArrayList<Integer> getPlayers(@Param("sport_id") Integer sport_id, @Param("team_id") Integer team_id);
}
