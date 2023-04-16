package com.spe.spandan.service;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.Teams;
import com.spe.spandan.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    public ResponseEntity<Message> addTeam(Map<String, String> requestMap) {
        Message success = new Message("Team added Successfully.");
        Message addFailed = new Message("Invalid Data");
        Message failed = new Message("Something Went Wrong at Teams Service.");
        try{
            if(validateAddTeam(requestMap)){
                teamsRepository.save(createTeamFromMap(requestMap));
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

    private Teams createTeamFromMap(Map<String, String> requestMap) {
        Teams t = new Teams();

        t.setSport_id(Integer.parseInt(requestMap.get("sport_id")));
        t.setName(requestMap.get("name"));

        return t;
    }

    private boolean validateAddTeam(Map<String, String> requestMap) {
        System.out.println(requestMap);
        if(requestMap.containsKey("sport_id") && requestMap.containsKey("name"))
        {
            return true;
        }
        return false;
    }
}
