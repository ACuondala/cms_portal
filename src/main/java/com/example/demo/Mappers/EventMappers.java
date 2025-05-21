package com.example.demo.Mappers;

import com.example.demo.Dtos.EventDto;
import com.example.demo.Dtos.EventRequestDto;
import com.example.demo.Models.Events;

import java.util.Optional;

public class EventMappers {

    public static EventDto modelToDto(Events event){
        return new EventDto(event.getId(),
                    event.getTitle(),
                    event.getAddress(),
                    event.getEventDate(),
                    event.getDescription(),
                    event.getFile(),
                    event.getCreatedAt()
                );
    }


    public static void dtoToModel(Events event, EventRequestDto eventDto){

        Optional.ofNullable(eventDto.title()).ifPresent(event::setTitle);
        Optional.ofNullable(eventDto.address()).ifPresent(event::setAddress);
        Optional.ofNullable(eventDto.eventDate()).ifPresent(event::setEventDate);
        Optional.ofNullable(eventDto.description()).ifPresent(event::setDescription);


    }
}
