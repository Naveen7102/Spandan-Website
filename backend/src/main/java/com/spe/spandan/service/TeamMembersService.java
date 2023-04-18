package com.spe.spandan.service;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.TeamMembers;
import com.spe.spandan.repository.TeamMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TeamMembersService {

    @Autowired
    TeamMembersRepository teamMembersRepository;
    public ResponseEntity<Message> addMember(Map<String, String> requestMap) {
        Message success = new Message("Fixture added Successfully.");
        Message addFailed = new Message("Invalid Data");
        Message failed = new Message("Something Went Wrong at Fixture Service.");
        try{
            if(validateAddMember(requestMap)){
                teamMembersRepository.save(createTeamMemberFromMap(requestMap));
                return new ResponseEntity<Message>(success, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Message>(addFailed, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private TeamMembers createTeamMemberFromMap(Map<String, String> requestMap) {
        TeamMembers t = new TeamMembers();

        t.setTeam_id(Integer.parseInt(requestMap.get("team_id")));
        t.setParticipant_id(Integer.parseInt(requestMap.get("participant_id")));
        t.setSport_id(Integer.parseInt(requestMap.get("sport_id")));

        return t;
    }

    private boolean validateAddMember(Map<String, String> requestMap) {
        System.out.println(requestMap);
        if(requestMap.containsKey("sport_id") && requestMap.containsKey("team_id") && requestMap.containsKey("participant_id"))
        {
            return true;
        }
        return false;
    }
}