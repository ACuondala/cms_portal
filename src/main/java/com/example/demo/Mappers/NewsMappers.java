package com.example.demo.Mappers;

import com.example.demo.Dtos.NewsDto;
import com.example.demo.Dtos.NewsRequestDto;
import com.example.demo.Models.News;

public class NewsMappers {

    public static NewsDto modelToDto(News news){
        return new NewsDto(news.getId(),
                           news.getTitle(),
                           news.getDescription(),
                news.getStatus().toString(),
                news.getFile(),
                news.getCreatedAt());
    }

    public static void dtoToModel(News news, NewsRequestDto newsDto){
       news.setTitle(newsDto.title());
       news.setDescription(newsDto.description());

    }
}
