package com.rahulshettyacademy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        when(libraryService.buildId(anyString(), anyInt())).thenReturn("1");
        when(libraryService.checkBookAlreadyExist("1")).thenReturn(false);

        ResponseEntity responseEntity = libraryController.addBookImplementation(this.buildLibrary());

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        AddResponse addResponse = (AddResponse) responseEntity.getBody();
        Assertions.assertEquals("1", addResponse.getId());
        Assertions.assertEquals("Success Book is Added", addResponse.getMsg());
    }

    @Test
    void addBookImplementationWithMock() throws Exception {
        Library library = this.buildLibrary();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(library);


        when(libraryService.buildId(anyString(), anyInt())).thenReturn(library.getId());
        when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(false);
        when(libraryRepository.save(any())).thenReturn(library);
        this.mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isCreated())
//                get json Object parameter with jsonPath from json object
                .andExpect(jsonPath("$.id").value(library.getId()));
    }

    @Disabled
    @Test
    void getBookById() {
    }

    @Test
    void getBookByAuthorName() throws Exception {
        List<Library> libraries = Arrays.asList(this.buildLibrary());

        when(libraryRepository.findAllByAuthor(anyString())).thenReturn(libraries);
        mockMvc.perform(get("/getBooks/author").param("authorname", "omidashouri"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id").value(libraries.get(0).getId()))
                .andExpect(jsonPath("$[0].id", is(libraries.get(0).getId())))
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*", is(notNullValue())));
/*                .andExpect(jsonPath("$.links[0].rel[0]", is("self")))
                .andExpect(jsonPath("$.links[0].href[0]", is("/"));*/
    }

    @Test
    void updateBook() throws Exception {
        Library oldLibrary = this.buildLibrary();
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedLibraryJson = objectMapper.writeValueAsString(this.updateLibrary());

        when(libraryService.getBookById(oldLibrary.getId())).thenReturn(oldLibrary);
        this.mockMvc.perform(put("/updateBook/" + oldLibrary.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedLibraryJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(oldLibrary.getId())))
                .andExpect(jsonPath("$.aisle", is(this.updateLibrary().getAisle())))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"book_name\":\"omid1\",\"id\":\"1\",\"isbn\":\"1\",\"aisle\":11,\"author\":\"omidashouri1\"}"));
    }

    @Test
    void deleteBookById() throws Exception {
        when(libraryService.getBookById(this.buildLibrary().getId())).thenReturn(this.buildLibrary());
        doNothing().when(libraryRepository).delete(this.buildLibrary());
        this.mockMvc.perform(delete("/deleteBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("Book is deleted"));
    }

    @Disabled
    @Test
    void getBooks() {
    }


    public Library buildLibrary() {
        Library library = new Library();
        library.setAisle(1);
        library.setBook_name("omid");
        library.setIsbn("1");
        library.setAuthor("omidashouri");
        library.setId("1");
        return library;

    }

    public Library updateLibrary() {
        Library library = new Library();
        library.setAisle(11);
        library.setBook_name("omid1");
        library.setIsbn("11");
        library.setAuthor("omidashouri1");
        library.setId("1");
        return library;
    }
}