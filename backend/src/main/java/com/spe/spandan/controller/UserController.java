package com.spe.spandan.controller;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.User;
import com.spe.spandan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "user/addUser")
    public ResponseEntity<Message> addUser(@RequestBody(required = true) Map<String, String> requestMap) {
        try{
            return userService.addUser(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at User Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "user/login")
    public ResponseEntity<User> login(@RequestBody(required = true) Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        User failed = new User();
        return new ResponseEntity<User>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "user/updateSPOC")
    public ResponseEntity<Message> updateSPOC(@RequestBody(required = true) String email){
        try{
            return userService.updateSPOC(email);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Message failed = new Message("Something Went Wrong at User Controller.");
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
