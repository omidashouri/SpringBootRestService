package com.rahulshettyacademy.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryControllerTest {

    @Autowired
    LibraryController libraryController;

    @Test
    void addBookImplementation() {
       ResponseEntity responseEntity = libraryController.addBookImplementation(this.buildLibrary());
        System.out.println(responseEntity.getStatusCode());
    }

    @Disabled
    @Test
    void getBookById() {
    }

    @Disabled
    @Test
    void getBookByAuthorName() {
    }

    @Disabled
    @Test
    void updateBook() {
    }

    @Disabled
    @Test
    void deleteBookById() {
    }

    @Disabled
    @Test
    void getBooks() {
    }


    public Library buildLibrary()
    {
        Library library =new Library();
        library.setAisle(1);
        library.setBook_name("omid");
        library.setIsbn("1");
        library.setAuthor("omidashouri");
        library.setId("1");
        return library;

    }
    public Library UpdateLibrary()
    {
        Library library =new Library();
        library.setAisle(1);
        library.setBook_name("omid");
        library.setIsbn("1");
        library.setAuthor("omidashouri");
        library.setId("1");
        return library;

    }
}