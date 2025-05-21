package com.example.demo.Services;

import com.example.demo.Dtos.SlidesDto.SlideRequestDto;
import com.example.demo.Enums.StatusEnum;
import com.example.demo.Exceptions.EntitiesNotFoundException;
import com.example.demo.Helpers.FileHelper;
import com.example.demo.Mappers.SlideMapper;
import com.example.demo.Models.Events;
import com.example.demo.Models.News;
import com.example.demo.Models.Slide;
import com.example.demo.Repositories.EventRepository;
import com.example.demo.Repositories.NewsRepository;
import com.example.demo.Repositories.SlideRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Dtos.SlidesDto.SlideDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SlideService {
    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private EventRepository eventRepository;


    @Transactional(readOnly = true)
    public List<SlideDto> getAll() {

        List<Slide> slides=slideRepository.findAll();

        return slides.stream().map(SlideMapper::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SlideDto findSlide(UUID id) {
     Slide slide=slideRepository.findById(id).orElseThrow(()-> new EntitiesNotFoundException("This id doesn't exist"));

        return SlideMapper.modelToDto(slide);
    }

    @Transactional
    public SlideDto saveSlide(SlideRequestDto dto) {
        Slide slide= new Slide();
        News news=(dto.news() != null)?newsRepository.findById(dto.news()).orElseThrow(()-> new EntitiesNotFoundException("this id doesn't exist")): null;
        Events events=(dto.events() != null)?eventRepository.findById(dto.events()).orElseThrow(()-> new EntitiesNotFoundException("this id doesn't exist")):null;

        slide.setFile(FileHelper.fileupload(dto.file()));
        slide.setStatus(dto.status() != null ? dto.status(): StatusEnum.ACTIVE);
        SlideMapper.dtoToModel(slide,dto,news,events);

        slide=slideRepository.save(slide);

        return SlideMapper.modelToDto(slide);
    }

    @Transactional
    public void delete(UUID id) {
        try{
            Slide slide=slideRepository.getReferenceById(id);
            slideRepository.delete(slide);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("this id doesn't exists");
        }
    }

    @Transactional
    public SlideDto updateSlide(SlideRequestDto requestDto, UUID id) {{
        try{
            Slide slide=slideRepository.getReferenceById(id);
            News news=(requestDto.news() !=null)?newsRepository.findById(requestDto.news()).
                    orElseThrow(()->new EntitiesNotFoundException("this id doesn't exists")):null;
            Events event=(requestDto.events() != null)?eventRepository.findById(requestDto.events()).
                    orElseThrow(()->new EntitiesNotFoundException("this id doesn't exists")):null;

            if(requestDto.file() != null){
                slide.setFile(FileHelper.fileupload(requestDto.file()));
            }
            slide.setStatus(requestDto.status() != null?requestDto.status():StatusEnum.ACTIVE);

            SlideMapper.dtoToModel(slide,requestDto,news,event);

            slide=slideRepository.save(slide);

            return SlideMapper.modelToDto(slide);

        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("this id doesn't exists");
        }
    }
    }
}

