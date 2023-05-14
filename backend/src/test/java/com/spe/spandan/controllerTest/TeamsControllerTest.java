package com.spe.spandan.controllerTest;

import com.spe.spandan.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String ADMIN_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuLmtAaW4iLCJ1c2VyX3R5cGUiOiJBZG1pbiIsImV4cCI6MTY4NDg1MTQ0NSwiaWF0IjoxNjgzOTg3NDQ1fQ._r8yb0mkxv6atqJ8LgfFYkVgXsqnm6K_DXq09dY7hVY";

    private static final String SPOC_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtLnNAaW4iLCJ1c2VyX3R5cGUiOiJTUE9DIiwiZXhwIjoxNjg0ODUxNTUwLCJpYXQiOjE2ODM5ODc1NTB9.bJJ3iRtbFQKwD_YQ25C8XcV_xP0XcfsIreJfRE01e28";

    private static final String PARTICIPANT_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NDg1MTcwOCwiaWF0IjoxNjgzOTg3NzA4fQ.R1-wVOCatA3My2JW3RcfdEpW41fWeXLQ15__UXyTFbs";
//    @Test
//    public void addTeamTest() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", ADMIN_AUTH_TOKEN);
//
//        Map<String, String> teamMap = new HashMap<>();
//        teamMap.put("name", "Test_Team4");
//        teamMap.put("sport_id", "1");
//
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(teamMap, headers);
//        ResponseEntity<Message> response = restTemplate.exchange("/teams/addTeam", HttpMethod.POST, request, Message.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Team added Successfully.", response.getBody().getMessage());
//    }

    @Test
    public void getTeamsTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ADMIN_AUTH_TOKEN);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ArrayList> response = restTemplate.exchange("/teams/getTeams/1", HttpMethod.GET, entity,ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }



}
