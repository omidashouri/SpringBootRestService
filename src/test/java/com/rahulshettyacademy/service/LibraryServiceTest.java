package com.rahulshettyacademy.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class LibraryServiceTest {

    @Test
    void buildId() {
        LibraryService libraryService = new LibraryService();

        String result = libraryService.buildId("omid",1);
        assertEquals("omid1",result);

        String result2 = libraryService.buildId("Zomid",1);
        assertEquals("OLDZomid1",result2);

    }


    void checkBookAlreadyExist() {
    }


    void getBookById() {
    }
}