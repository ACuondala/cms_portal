package com.example.demo.Services;

import com.example.demo.Dtos.EventDto;
import com.example.demo.Dtos.EventRequestDto;
import com.example.demo.Enums.StatusEnum;
import com.example.demo.Exceptions.EntitiesNotFoundException;
import com.example.demo.Helpers.FileHelper;
import com.example.demo.Helpers.StatusHelper;
import com.example.demo.Mappers.EventMappers;
import com.example.demo.Models.Events;
import com.example.demo.Repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public List<EventDto> getAll() {
        List<Events> events=repository.findAll();

        return events.stream().map(EventMappers::modelToDto).collect(Collectors.toList());
    }

    @Transactional
    public EventDto saveEvent(EventRequestDto eventDto) {
        System.out.println("EVENT DTO: " + eventDto);
        Events events=new Events();
        EventMappers.dtoToModel(events,eventDto);

        events.setFile(FileHelper.fileupload(eventDto.file()));

        events.setStatus(eventDto.status() != null? eventDto.status(): StatusEnum.ACTIVE);
        events=repository.save(events);
        return EventMappers.modelToDto(events);
    }

    @Transactional(readOnly = true)
    public EventDto getOne(UUID id) {
        Events events= repository.findById(id)
                .orElseThrow(()-> new EntitiesNotFoundException("This id doesn't exist"));

        return EventMappers.modelToDto(events);
    }

    @Transactional
    public EventDto updateEvent(UUID id, EventRequestDto eventRequestDto) {
        try {
            Events event = repository.getReferenceById(id);
            EventMappers.dtoToModel(event,eventRequestDto);
            if(eventRequestDto.file() != null) {
                event.setFile(FileHelper.fileupload(eventRequestDto.file()));
            }

            if(eventRequestDto.status() != null ){

               event.setStatus(eventRequestDto.status());
            }
                repository.save(event);
            return EventMappers.modelToDto(event);
        }catch(EntityNotFoundException e){

            throw new EntityNotFoundException("This event id doesn't exist");
        }

    }

}
