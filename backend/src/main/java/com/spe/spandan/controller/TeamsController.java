package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.service.TeamsService;
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

@CrossOrigin(origins = "*")
@RestController
public class TeamsController {
    @Autowired
    TeamsService teamsService;

    @PostMapping(path = "teams/addTeam")
    public ResponseEntity<Message> addTeam(@RequestBody(required = true) Map<String, String> requestMap) {
        Message failed = new Message("Something Went Wrong at Teams Controller.");
        try{
            return teamsService.addTeam(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
