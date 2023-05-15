package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.User;
import com.spe.spandan.service.TeamMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
public class TeamMembersController {
    private static final Logger logger = LoggerFactory.getLogger(TeamMembersController.class);
    @Autowired
    TeamMembersService teamMembersService;

    @PostMapping(path = "teamMembers/addMember")
    public ResponseEntity<Message> addMember(@RequestBody(required = true) Map<String, String> requestMap) {
        logger.info("[addMember] - " + requestMap);
        Message failed = new Message("Something Went Wrong at TeamMember Controller.");
        try{
            return teamMembersService.addMember(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at TeamMembers Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "teamMembers/getPlayers")
    public ResponseEntity<ArrayList<String>> getPlayers(@RequestParam(required = true) Integer sport_id, @RequestParam(required = true) String team){
        logger.info("[getPlayers] - " + team);
        try{
//            System.out.println(requestMap);
            return teamMembersService.getPlayers(sport_id,team);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ArrayList<String> failed = new ArrayList<>();
        logger.error("[ERROR] - Something Went Wrong at TeamMembers Controller.");
        return new ResponseEntity<ArrayList<String>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "teamMembers/getPlayersDetails")
    public ResponseEntity<ArrayList<User>> getPlayersDetails(@RequestParam(required = true) Integer sport_id, @RequestParam(required = true) String team){
        logger.info("[getPlayersDetails] - " + team);
        try{
            return teamMembersService.getPlayersDetails(sport_id,team);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ArrayList<User> failed = new ArrayList<>();
        logger.error("[ERROR] - Something Went Wrong at TeamMembers Controller.");
        return new ResponseEntity<ArrayList<User>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
