package com.spe.spandan.controller;

import com.spe.spandan.model.FixtureList;
import com.spe.spandan.model.FixtureReturn;
import com.spe.spandan.model.Message;
import com.spe.spandan.service.FixturesService;
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
public class FixturesController {
    @Autowired
    FixturesService fixturesService;

    @PostMapping(path = "fixtures/addFixture")
    public ResponseEntity<Message> addFixture(@RequestBody(required = true) Map<String, String> requestMap) {
        Message failed = new Message("Something Went Wrong at Fixture Controller.");
        try{
            return fixturesService.addFixture(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "fixtures/getFixtures/{sport}")
    public ResponseEntity<FixtureList> getFixtures(@PathVariable String sport){
        FixtureList failed = new FixtureList();
        try{
            return fixturesService.getFixtures(sport);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<FixtureList>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
