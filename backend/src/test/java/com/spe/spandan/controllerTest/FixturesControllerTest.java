package com.spe.spandan.controllerTest;

import com.spe.spandan.model.Message;
import com.spe.spandan.model.User;
import com.spe.spandan.repository.UserRepository;
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
public class FixturesControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String ADMIN_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String SPOC_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String PARTICIPANT_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";
    @Test
    public void testAddFixture() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", SPOC_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("sport_id", "1");
        requestBody.put("time", "15-05-2023 22:00:00");
        requestBody.put("team1", "Test_Team3");
        requestBody.put("team2", "Test_Team4");

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/fixtures/addFixture", requestEntity, Message.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Fixture added Successfully.", responseEntity.getBody().getMessage());
    }

    @Test
    public void testGetFixtures() throws Exception {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", PARTICIPANT_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // Act
        ResponseEntity<ArrayList> response = restTemplate.exchange("/fixtures/getFixtures/1", HttpMethod.GET, entity, ArrayList.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testUpdateResult() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", SPOC_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("sport_id", "1");
        requestBody.put("winner", "Test_Team1");
        requestBody.put("result", "Won by 20 runs");
        requestBody.put("id", "1");

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/fixtures/updateResult", requestEntity, Message.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Fixture updated Successfully.", responseEntity.getBody().getMessage());
    }

}
