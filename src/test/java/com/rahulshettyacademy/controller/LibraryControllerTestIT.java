package com.rahulshettyacademy.controller;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class LibraryControllerTestIT {

    // 'IT' at the end of class name mean integration test and sonar suppose it as integration test
    // use 'TestRestTemplate' for integration test or 'Rest Assured', but first is spring built in

    TestRestTemplate restTemplate;

    @BeforeEach
    public void init(){
        restTemplate = new TestRestTemplate();
    }


    @Test
    public void getAuthorNameBooksTest() throws JSONException {
        String expected = "[\n" +
                "    {\n" +
                "        \"book_name\": \"omid book 2\",\n" +
                "        \"id\": \"2\",\n" +
                "        \"isbn\": \"isbn 2\",\n" +
                "        \"aisle\": 2,\n" +
                "        \"author\": \"author ashouri 2\"\n" +
                "    }\n" +
                "]";
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:8080/getBooks/author?authorname=author ashouri 2",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected,response.getBody(),false);

    }


}
