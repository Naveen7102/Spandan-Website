package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.Sports;
import com.spe.spandan.repository.SportsRepository;
import com.spe.spandan.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
public class SportsController {
    private static final Logger logger = LoggerFactory.getLogger(SportsController.class);

    @Autowired
    SportsService sportsService;

    @Autowired
    SportsRepository sportsRepository;

    @PostMapping(path = "sport/addSport")
    public ResponseEntity<Message> addSport(@RequestBody(required = true) String sport) {
        logger.info("[addSport] - " + sport);
        Message failed = new Message("Something Went Wrong at Sports Controller.");
        try{
            return sportsService.addSport(sport);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at Sports Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "sport/getSports")
    public ResponseEntity<ArrayList<Sports>> getSports() {
        logger.info("[getSports]");
        try{
            ArrayList<Sports> s = sportsRepository.getSports();
            return new ResponseEntity<ArrayList<Sports>>(s, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ArrayList<Sports> failed = new ArrayList<Sports>();
        logger.error("[ERROR] - Something Went Wrong at Sports Controller.");
        return new ResponseEntity<ArrayList<Sports>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
