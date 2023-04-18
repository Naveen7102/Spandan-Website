package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.Rules;
import com.spe.spandan.model.StartDate;
import com.spe.spandan.repository.RulesRepository;
import com.spe.spandan.repository.StartDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class RulesNDateController {
    @Autowired
    StartDateRepository startDateRepository;

    @Autowired
    RulesRepository rulesRepository;

    @PostMapping(path = "date/addStartDate")
    public ResponseEntity<Message> addDate(@RequestBody(required = true) Date date) {
        try{
            StartDate sd = new StartDate(date);
            startDateRepository.save(sd);
            Message success = new Message("Date added Successfully.");
            return new ResponseEntity<Message>(success, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "date/getStartDate")
    public ResponseEntity<StartDate> getStartDate(){
        try{
            return new ResponseEntity<StartDate>(startDateRepository.getDate(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<StartDate>(new StartDate(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(path = "rules/addRule")
    public ResponseEntity<Message> addRule(@RequestBody(required = true) Map<String, String> requestMap) {
        try{
            Rules r = new Rules();
            r.setRule(requestMap.get("rule").getBytes());
            r.setSport_id(Integer.parseInt(requestMap.get("sport_id")));
            rulesRepository.save(r);
            Message success = new Message("Rule added Successfully.");
            return new ResponseEntity<Message>(success, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "rules/getRule")
    public ResponseEntity<Rules> getRule(){
        try{
            return new ResponseEntity<Rules>(rulesRepository.getRule(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Rules>(new Rules(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
