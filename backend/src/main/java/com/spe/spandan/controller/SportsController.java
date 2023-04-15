package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class SportsController {
    @Autowired
    SportsService sportsService;

    @PostMapping(path = "sports/addSport")
    public ResponseEntity<Message> addSport(@RequestBody(required = true) String sport) {
        Message failed = new Message("Something Went Wrong at Sports Controller.");
        try{
            return sportsService.addSport(sport);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
