package com.rahulshettyacademy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulshettyacademy.repository.LibraryRepository;
import com.rahulshettyacademy.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class LibraryControllerTest {

    @Autowired
    LibraryController libraryController;

    @MockBean
    LibraryService libraryService;

    @MockBean
    LibraryRepository libraryRepository;

    @Autowired
    MockMvc mockMvc;


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

    @Test
    void addBookImplementationWithMock() throws Exception {
        Library library = this.buildLibrary();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(library);


        when(libraryService.buildId(anyString(),anyInt())).thenReturn("1");
        when(libraryService.checkBookAlreadyExist("1")).thenReturn(false);
        when(libraryRepository.save(any())).thenReturn(library);
        this.mockMvc.perform(post("/addBook")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject))
                    .andExpect(status().isCreated());
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