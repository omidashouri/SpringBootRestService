package com.rahulshettyacademy.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest
public class LibraryControllerTestIT {

    // 'IT' at the end of class name mean integration test and sonar suppose it as integration test
    // use 'TestRestTemplate' for integration test or 'Rest Assured', but first is spring built in


    public void getAuthorNameBooksTest(){
        TestRestTemplate restTemplate = new TestRestTemplate();
//        restTemplate.getForEntity("")

    }


}
