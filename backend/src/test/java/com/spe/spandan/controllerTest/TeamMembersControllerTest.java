package com.spe.spandan.controllerTest;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamMembersControllerTest {

    private static final String ADMIN_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String SPOC_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String PARTICIPANT_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testAddMember() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ADMIN_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("sport_id", "1");
        requestBody.put("team", "Test_Team2");
        requestBody.put("participant_id", "2");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Message> response = restTemplate.postForEntity("/teamMembers/addMember", entity, Message.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Member already in same team", response.getBody().getMessage());
    }

    @Test
    public void testGetPlayers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ADMIN_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare test data
        Integer sportId = 1;
        String team = "Test_Team1";

        // Set up the request URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/teamMembers/getPlayers")
                .queryParam("sport_id", sportId)
                .queryParam("team", team);
        String url = builder.toUriString();

        // Make GET request to endpoint
        ResponseEntity<ArrayList> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                ArrayList.class);

        // Verify response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the returned list contains players
        List<String> players = response.getBody();
        assertNotNull(players);
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetPlayerDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ADMIN_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare test data
        Integer sportId = 1;
        String team = "Test_Team1";

        // Set up the request URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/teamMembers/getPlayersDetails")
                .queryParam("sport_id", sportId)
                .queryParam("team", team);
        String url = builder.toUriString();

        // Make GET request to endpoint
        ResponseEntity<ArrayList> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                ArrayList.class);

        // Verify response status code
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify that the returned list contains players
        List<User> players = response.getBody();
        assertNotNull(players);
        assertTrue(response.getBody().size() > 0);
    }


}
