package com.rahulshettyacademy.controller;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

@SpringBootTest
public class LibraryControllerTestIT {

    // 'IT' at the end of class name mean integration test and sonar suppose it as integration test
    // use 'TestRestTemplate' for integration test or 'Rest Assured', but first is spring built in

    TestRestTemplate testRestTemplate;
    HttpHeaders httpHeaders;

    @BeforeEach
    public void init(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        testRestTemplate = new TestRestTemplate();

        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
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
        ResponseEntity<String> response = testRestTemplate
                .getForEntity("http://localhost:8080/getBooks/author?authorname=author ashouri 2",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected,response.getBody(),false);

    }

    @Test
    public void addBooksIntegrationTest() throws JSONException {

        HttpEntity<Library> libraryHttpEntity = new HttpEntity<>(this.buildLibrary(),httpHeaders);

        ResponseEntity<String> addResponseResponseEntity =  testRestTemplate
                .postForEntity("http://localhost:8080/addBook",libraryHttpEntity,String.class);

        Assert.assertEquals(HttpStatus.CREATED,addResponseResponseEntity.getStatusCode());
//        Assert.assertEquals(addResponseResponseEntity.getHeaders().get("Developer Name").get(0),"Omid Ashouri");


    }


    public Library buildLibrary()
    {
        Library lib =new Library();
        lib.setAisle(4);
        lib.setBook_name("omid 4");
        lib.setIsbn("ISBN 4");
        lib.setAuthor("omid ashouri 4");
        lib.setId("4");
        return lib;

    }
}
