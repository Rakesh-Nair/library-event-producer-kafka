package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.producer.LibraryEventsProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnkafka.domain.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class LibraryEventsController {

	private final LibraryEventsProducer libraryEventsProducer;

	public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
		this.libraryEventsProducer = libraryEventsProducer;
	}
	
	@PostMapping("/v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibraryEvent(
			@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
				
		//Post Library Event to Kafka
		//libraryEventsProducer.sendLibraryEvent(libraryEvent);
		//libraryEventsProducer.sendLibraryEventApproach2(libraryEvent);
		libraryEventsProducer.sendLibraryEvent_approach3(libraryEvent);
		log.info("Library Event : {} ",libraryEvent);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
		
		
	}
	
	@GetMapping("/v1/libraryevent/test")
	public String testLibraryEvent(){
				
		//Post Library Event to Kafka
		log.info("Enter Get Method ");
		return "Hi this is a Response";
		
		
	}

}
