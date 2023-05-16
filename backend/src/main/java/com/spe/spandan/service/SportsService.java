package com.spe.spandan.service;


import com.spe.spandan.model.Message;
import com.spe.spandan.model.Sports;
import com.spe.spandan.repository.SportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SportsService {

    @Autowired
    SportsRepository sportsRepository;
    public ResponseEntity<Message> addSport(String sport) {
        Message success = new Message("Sport added Successfully.");

        try{
            Integer id = sportsRepository.getSportId(sport);
            if(id == null){
                Sports s = new Sports(sport);
                sportsRepository.save(s);
                return new ResponseEntity<Message>(success, HttpStatus.OK);
            }
            Message addfailed = new Message("Sport Already exists.");
            return new ResponseEntity<Message>(addfailed, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at Sports Service.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
