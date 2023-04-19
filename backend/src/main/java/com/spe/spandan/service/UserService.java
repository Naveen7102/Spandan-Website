package com.spe.spandan.service;

import com.spe.spandan.model.Message;
import com.spe.spandan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

import com.spe.spandan.model.User;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Message> addUser(Map<String, String> requestMap) {
        Message success = new Message("User added Successfully.");
        Message addFailed = new Message("Invalid Data");
        Message failed = new Message("Something Went Wrong at User Service.");
        try{
            if(validateAddUser(requestMap)){
                userRepository.save(createUserFromMap(requestMap));
                return new ResponseEntity<Message>(success, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Message>(addFailed, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private User createUserFromMap(Map<String, String> requestMap) {
        User user = new User();

        user.setUserType(requestMap.get("user_type"));
        user.setPassword(requestMap.get("password"));
        user.setUsername(requestMap.get("username"));
        user.setEmail(requestMap.get("email"));
        user.setPhone_no(requestMap.get("phone_no"));

        return user;
    }

    private boolean validateAddUser(Map<String, String> requestMap) {
        System.out.println(requestMap);
        if(requestMap.containsKey("username") && requestMap.containsKey("password") && requestMap.containsKey("user_type") && requestMap.containsKey("email") && requestMap.containsKey("phone_no"))
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<User> login(Map<String, String> requestMap) {
        try{
            String email = requestMap.get("email");
            String password = requestMap.get("password");

            User u = userRepository.getUser(email);
            if(u.getPassword().equals(password)) {
                return new ResponseEntity<User>(u, HttpStatus.OK);
            }
            else{
                User failed = new User();
                return new ResponseEntity<User>(failed, HttpStatus.BAD_REQUEST);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        User failed = new User();
        return new ResponseEntity<User>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
