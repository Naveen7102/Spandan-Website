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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class RulesNDateController {
    @Autowired
    StartDateRepository startDateRepository;

    @Autowired
    RulesRepository rulesRepository;

    @Transactional
    @PostMapping(path = "date/addStartDate")
    public ResponseEntity<Message> addDate(@RequestBody(required = true) Map<String, String> requestMap) {
        try{
            startDateRepository.updateDate(requestMap.get("date"));
            Message success = new Message("Date added Successfully.");
            return new ResponseEntity<Message>(success, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "date/getDate")
    public ResponseEntity<StartDate> getDate(){
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
            System.out.println(requestMap.get("rule").getBytes());
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

    @GetMapping(path = "rules/getRule/{sport_id}")
    public ResponseEntity<ArrayList<String>> getRule(@PathVariable Integer sport_id){
        try{
            ArrayList<String> rulesReturn = new ArrayList<>();
            ArrayList<Rules> rules = rulesRepository.getRule(sport_id);
//            System.out.println(rules);
            if(rules != null){
                for(Rules r:rules){

                    String rule = new String(r.getRule());
                    rulesReturn.add(rule);
                }
            }
            return new ResponseEntity<ArrayList<String>>(rulesReturn, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<ArrayList<String>>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
