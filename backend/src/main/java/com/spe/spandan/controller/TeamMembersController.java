package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
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

@CrossOrigin(origins = "*")
@RestController
public class TeamMembersController {
    @Autowired
    TeamMembersService teamMembersService;

    @PostMapping(path = "teamMembers/addMember")
    public ResponseEntity<Message> addMember(@RequestBody(required = true) Map<String, String> requestMap) {
        Message failed = new Message("Something Went Wrong at TeamMember Controller.");
        try{
            return teamMembersService.addMember(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}