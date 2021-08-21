package com.rahulshettyacademy;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rahulshettyacademy.controller.Library;
import com.rahulshettyacademy.repository.LibraryRepository;



@SpringBootApplication
public class SpringBootRestServiceApplication // implements CommandLineRunner
{
	
	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
	
//	@Override
//	public void run(String[] args)
//	{

/*		System.out.println(".......find by id.......");
		Library lib= repository.findById("studio2222").get();
		System.out.println(lib.getAuthor());*/

/*		System.out.println(".......save.......");
		Library en =new Library();
		en.setAisle(1232211);
		en.setAuthor("omid");
		en.setBook_name("omidbook");
		en.setIsbn("lkhs");
		en.setId("lkhs123");
		repository.save(en);*/

/*		System.out.println(".......find all.......");
		List<Library> allrecords =repository.findAll();
		for(Library item : allrecords)
		{
			System.out.println(item.getBook_name());
		}*/

/*		System.out.println(".......delete.......");
		repository.delete(en);*/

//	}

}
