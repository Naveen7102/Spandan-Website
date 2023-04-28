package com.spe.spandan.service;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.Teams;
import com.spe.spandan.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;


@Service
public class TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    public ResponseEntity<Message> addTeam(Map<String, String> requestMap) {
        Message success = new Message("Team added Successfully.");
        try{
            if(validateAddTeam(requestMap)){
                Integer id = teamsRepository.getTeamId(requestMap.get("name"), Integer.parseInt(requestMap.get("sport_id")));
                if(id == null){
                    teamsRepository.save(createTeamFromMap(requestMap));
                    return new ResponseEntity<Message>(success, HttpStatus.OK);
                }
                Message addFailed2 = new Message("Team already exists");
                return new ResponseEntity<Message>(addFailed2, HttpStatus.BAD_REQUEST);
            }
            else {
                Message addFailed = new Message("Invalid Data");
                return new ResponseEntity<Message>(addFailed, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Teams Service.");
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

    public ResponseEntity<ArrayList<Teams>> getTeams(Integer sport_id) {
        try{
            ArrayList<Teams> t = teamsRepository.getTeams(sport_id);
            return new ResponseEntity<ArrayList<Teams>>(t, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<Teams> failed = new ArrayList<>();
        return new ResponseEntity<ArrayList<Teams>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
