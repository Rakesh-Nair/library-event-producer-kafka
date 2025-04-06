package com.learnkafka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnkafka.domain.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LibraryEventsController {
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(
			@RequestBody LibraryEvent libraryEvent){
				
		//Post Library Event to Kafka
		log.info("Library Event Posted "+libraryEvent);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
		
		
	}
	
	@GetMapping("/v1/libraryevent/test")
	public String testLibraryEvent(){
				
		//Post Library Event to Kafka
		log.info("Enter Get Method ");
		return "Hi this is a Response";
		
		
	}

}
