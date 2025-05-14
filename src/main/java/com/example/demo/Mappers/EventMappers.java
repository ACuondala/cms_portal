package com.example.demo.Mappers;

import com.example.demo.Dtos.EventDto;
import com.example.demo.Dtos.EventRequestDto;
import com.example.demo.Models.Events;

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
        event.setTitle(eventDto.title());
        event.setAddress(eventDto.address());
        event.setEventDate(eventDto.eventDate());
        event.setDescription(eventDto.description());
    }
}
