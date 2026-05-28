package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.domain.LibraryEventType;
import com.learnkafka.producer.LibraryEventsProducer;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
			@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
				
		//Post Library Event to Kafka
		//libraryEventsProducer.sendLibraryEvent(libraryEvent);
		libraryEventsProducer.sendLibraryEventApproach2(libraryEvent);
		//libraryEventsProducer.sendLibraryEvent_approach3(libraryEvent);
		log.info("Library Event : {} ",libraryEvent);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}

	@PutMapping("/v1/libraryevent")
	public ResponseEntity<?> updateLibraryEvent(
			@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {

		final ResponseEntity<String> BAD_REQUEST = validateLibraryEvent(libraryEvent);
		if (BAD_REQUEST != null) return BAD_REQUEST;

		//Post Library Event to Kafka
		//libraryEventsProducer.sendLibraryEvent(libraryEvent);
		//libraryEventsProducer.sendLibraryEventApproach2(libraryEvent);
		libraryEventsProducer.sendLibraryEvent_approach3(libraryEvent);
		log.info("Library Event : {} ",libraryEvent);
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}

	@Nullable
	private static ResponseEntity<String> validateLibraryEvent(LibraryEvent libraryEvent) {
		if(libraryEvent.libraryEventId() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the LibraryEventId");
		}

		if(!libraryEvent.libraryEventType().equals(LibraryEventType.UPDATE)){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only UPDATE event type is supported");
		}
		return null;
	}

	@GetMapping("/v1/libraryevent/test")
	public String testLibraryEvent(){
				
		//Post Library Event to Kafka
		log.info("Enter Get Method ");
		return "Hi this is a Response";
		
		
	}

}
