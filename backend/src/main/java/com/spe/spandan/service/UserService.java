package com.spe.spandan.service;

import com.spe.spandan.JWT.CustomerUsersDetailsService;
import com.spe.spandan.JWT.JwtFilter;
import com.spe.spandan.JWT.JwtUtil;
import com.spe.spandan.model.Message;
import com.spe.spandan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

import com.spe.spandan.model.User;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseEntity<Message> addUser(Map<String, String> requestMap) {
        Message success = new Message("User added Successfully.");
        Message exists = new Message("User already exists");
        Message addFailed = new Message("Invalid Data");
        Message failed = new Message("Something Went Wrong at User Service.");
        try{
            if(validateAddUser(requestMap)){
                if(Objects.isNull(userRepository.getUser(requestMap.get("email")))) {
                    userRepository.save(createUserFromMap(requestMap));
                    return new ResponseEntity<Message>(success, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<Message>(exists, HttpStatus.BAD_REQUEST);
                }
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
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password")));

            if(auth.isAuthenticated()){
                String token = jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),customerUsersDetailsService.getUserDetail().getUserType());
                customerUsersDetailsService.getUserDetail().setToken(token);
                return new ResponseEntity<>(customerUsersDetailsService.getUserDetail(),HttpStatus.OK);

            }

//            String email = requestMap.get("email");
//            String password = requestMap.get("password");
//
//            User u = userRepository.getUser(email);
//            if(u.getPassword().equals(password)) {
//                return new ResponseEntity<User>(u, HttpStatus.OK);
//            }
//            else{
//                User failed = new User();
//                return new ResponseEntity<User>(failed, HttpStatus.BAD_REQUEST);
//            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        User failed = new User();
        return new ResponseEntity<User>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public ResponseEntity<Message> updateSPOC(String email) {
        Message success = new Message("User added Successfully.");
        Message failed = new Message("Something Went Wrong at User Service.");
        try{
            userRepository.updateSPOC(email);
            return new ResponseEntity<Message>(success, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(failed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
