package com.spe.spandan.controllerTest;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.User;
import com.spe.spandan.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static final String ADMIN_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuLmtAaW4iLCJ1c2VyX3R5cGUiOiJBZG1pbiIsImV4cCI6MTY4NDg1MTQ0NSwiaWF0IjoxNjgzOTg3NDQ1fQ._r8yb0mkxv6atqJ8LgfFYkVgXsqnm6K_DXq09dY7hVY";

    private static final String SPOC_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtLnNAaW4iLCJ1c2VyX3R5cGUiOiJTUE9DIiwiZXhwIjoxNjg0ODUxNTUwLCJpYXQiOjE2ODM5ODc1NTB9.bJJ3iRtbFQKwD_YQ25C8XcV_xP0XcfsIreJfRE01e28";

    private static final String PARTICIPANT_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NDg1MTcwOCwiaWF0IjoxNjgzOTg3NzA4fQ.R1-wVOCatA3My2JW3RcfdEpW41fWeXLQ15__UXyTFbs";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

//    @Test
//    public void testAddUser() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("username", "Venkata");
//        requestBody.put("password", "1234");
//        requestBody.put("email", "v.s@in");
//        requestBody.put("phone_no", "9292");
//        requestBody.put("user_type", "Participant");
//
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<Message> response = restTemplate.postForEntity("/user/addUser", entity, Message.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("User added Successfully.", response.getBody().getMessage());
//    }

    @Test
    public void testLogin() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("email", "d.s@in");
        requestMap.put("password", "1234");

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestMap, headers);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity("/user/login", requestEntity, User.class);

        System.out.println(responseEntity.getBody().getToken());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("d.s@in", responseEntity.getBody().getEmail());
        assertEquals("9191", responseEntity.getBody().getPhone_no());
        assertEquals("Durga", responseEntity.getBody().getUsername());
        assertEquals("Participant", responseEntity.getBody().getUserType());

    }

    @Test
    public void testUpdateSPOC() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ADMIN_AUTH_TOKEN);
        headers.setContentType(MediaType.TEXT_PLAIN);

        String email = "m.s@in";

        HttpEntity<String> requestEntity = new HttpEntity<>(email, headers);

        ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/user/updateSPOC", requestEntity, Message.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("SPOC updated successfully", responseEntity.getBody().getMessage());

    }


}