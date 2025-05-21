package com.example.demo.Mappers;

import com.example.demo.Dtos.SlidesDto.SlideDto;
import com.example.demo.Dtos.SlidesDto.SlideRequestDto;
import com.example.demo.Models.Events;
import com.example.demo.Models.News;
import com.example.demo.Models.Slide;

public class SlideMapper {


    public static SlideDto modelToDto(Slide slide){
        return new SlideDto(slide.getId(),
                slide.getTitle(),
                slide.getSubTitle(),
                slide.getFile(),
                slide.getStatus().toString(),
                NewsMappers.modelToDto(slide.getNews().getSlide().getNews()),
                EventMappers.modelToDto(slide.getEvent())


                );
    }

    public static void dtoToModel(Slide slide, SlideRequestDto slideDto, News news, Events event){

        slide.setTitle(slideDto.title());
        slide.setSubTitle(slideDto.subTitle());


            slide.setNews(news);


            slide.setEvent(event);


    }
}
