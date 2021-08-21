package com.rahulshettyacademy.controller;

import com.rahulshettyacademy.repository.LibraryRepository;
import com.rahulshettyacademy.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibraryControllerTest {

    @Autowired
    LibraryController libraryController;

    @MockBean
    LibraryService libraryService;

    @MockBean
    LibraryRepository libraryRepository;



    @Test
    void addBookImplementation() {
        when(libraryService.buildId(anyString(),anyInt())).thenReturn("1");
        when(libraryService.checkBookAlreadyExist("1")).thenReturn(false);

        ResponseEntity responseEntity = libraryController.addBookImplementation(this.buildLibrary());

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());

        AddResponse addResponse = (AddResponse) responseEntity.getBody();
        Assertions.assertEquals("1",addResponse.getId());
        Assertions.assertEquals("Success Book is Added",addResponse.getMsg());
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