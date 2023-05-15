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
public class SportsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String ADMIN_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String SPOC_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";

    private static final String PARTICIPANT_AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkLnNAaW4iLCJ1c2VyX3R5cGUiOiJQYXJ0aWNpcGFudCIsImV4cCI6MTY4NTAyMjQ2NCwiaWF0IjoxNjg0MTU4NDY0fQ.QipOH7YyWSeUVSiYlb5kjHUtSYs05td6LyMVEARncT8";
//    @Test
//    public void testAddSport() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", ADMIN_AUTH_TOKEN);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String requestBody = "Chess";
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/sport/addSport", requestEntity, Message.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals("Sport added Successfully.", responseEntity.getBody().getMessage());
//
//    }

    @Test
    public void testGetSports() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", PARTICIPANT_AUTH_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ArrayList> responseEntity = restTemplate.exchange("/sport/getSports", HttpMethod.GET, requestEntity, ArrayList.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().size() > 0);
    }
}
