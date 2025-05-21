package com.example.demo.Controllers;

import com.example.demo.Dtos.EventDto;
import com.example.demo.Dtos.EventRequestDto;
import com.example.demo.Dtos.NewsDto;
import com.example.demo.Response.SuccessResponse;
import com.example.demo.Services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/events")
@Tag(name="Event")
public class EventController {


    @Autowired
    private EventService service;

    @GetMapping
    @Operation(description = "Show all events")
    public ResponseEntity<SuccessResponse<List<EventDto>>> index(){
        List<EventDto> eventDto=service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Event find successfully",eventDto));
    }

    @PostMapping
    @Operation(description = "store a new event")
    public ResponseEntity<SuccessResponse<EventDto>> store(@ModelAttribute EventRequestDto event){

        EventDto eventDto=service.saveEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Event created successfully",eventDto));
    }

    @GetMapping(value="/{id}")
    @Operation(description = "Show a single event")
    public ResponseEntity<EventDto> show(@PathVariable UUID id){

        EventDto eventDto=service.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(eventDto);
    }

    @PutMapping(value="/{id}")
    @Operation(description = "Update an expecific event")
    public ResponseEntity<SuccessResponse<EventDto>> update(@PathVariable UUID id, @ModelAttribute EventRequestDto eventRequestDto){
        EventDto eventDto=service.updateEvent(id,eventRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Event update successfully",eventDto));
    }

    @DeleteMapping(value="/{id}")
    @Operation(description = "Delete an event")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        return ResponseEntity.noContent().build();
    }



}


