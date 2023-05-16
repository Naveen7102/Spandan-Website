package com.spe.spandan.controller;

import com.spe.spandan.model.FixtureReturn;
import com.spe.spandan.model.Message;
import com.spe.spandan.service.FixturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
public class FixturesController {

    private static final Logger logger = LoggerFactory.getLogger(FixturesController.class);
    @Autowired
    FixturesService fixturesService;

    @PostMapping(path = "fixtures/addFixture")
    public ResponseEntity<Message> addFixture(@RequestBody(required = true) Map<String, String> requestMap) {
        logger.info("[addFixture] - adding a new fixture");
        Message failed = new Message("Something Went Wrong at Fixture Controller.");
        try{
            return fixturesService.addFixture(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at Fixture Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "fixtures/getFixtures/{sport}")
    public ResponseEntity<ArrayList<FixtureReturn>> getFixtures(@PathVariable Integer sport){
        logger.info("[getFixtures] - getting fixtures for " + sport);
        ArrayList<FixtureReturn> failed = new ArrayList<FixtureReturn>();
        try{
            return fixturesService.getFixtures(sport);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at Fixture Controller.");
        return new ResponseEntity<ArrayList<FixtureReturn>>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "fixtures/updateResult")
    public ResponseEntity<Message> updateResult(@RequestBody(required = true) Map<String, String> requestMap){
        logger.info("[updateResult] - Updating the result");
        Message failed = new Message("Something Went Wrong at Fixture Controller.");
        try{
            return fixturesService.updateResult(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at Fixture Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "fixtures/deleteFixture")
    public ResponseEntity<Message> deleteFixture(@RequestParam Integer id){
        logger.info("[deleteFixture] - getting fixtures for " + id);
        Message failed = new Message("Something Went Wrong at Fixture Controller.");
        try{
            return fixturesService.deleteFixture(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.error("[ERROR] - Something Went Wrong at Fixture Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
