package com.apis.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    void testHealthCheck() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String url = "http://localhost:".concat(String.valueOf(randomServerPort)).concat("/employee/health/check");
        URI uri = new URI(url);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testHealthErrorCheck() throws URISyntaxException {
        TestRestTemplate restTemplate = new TestRestTemplate();

        final String url = "http://localhost:".concat(String.valueOf(randomServerPort)).concat("/employee/health/error");
        URI uri = new URI(url);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        
        assertEquals(500, response.getStatusCodeValue());
    }
}
